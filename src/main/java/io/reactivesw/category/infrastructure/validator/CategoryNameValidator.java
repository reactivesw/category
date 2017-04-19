package io.reactivesw.category.infrastructure.validator;

import io.reactivesw.category.application.model.CategoryDraft;
import io.reactivesw.category.domain.model.Category;
import io.reactivesw.category.domain.model.LocalizedStringValue;
import io.reactivesw.category.infrastructure.util.CategoryUtils;
import io.reactivesw.exception.AlreadyExistException;
import io.reactivesw.exception.ParametersException;
import io.reactivesw.model.LocalizedString;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Validator for category name.
 */
public final class CategoryNameValidator {

  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(CategoryNameValidator.class);

  /**
   * Instantiates a new CategoryView validator.
   */
  private CategoryNameValidator() {
  }

  /**
   * Validate null.
   *
   * @param draft the draft
   */
  public static void validateNull(CategoryDraft draft) {

    if (draft.getName() == null
        || draft.getName().getLocalized() == null
        || draft.getName().getLocalized().isEmpty()) {
      LOG.debug("CategoryView name can not be null");
      throw new ParametersException("CategoryView name can not be null");
    }
  }

  /**
   * Equal validate.
   *
   * @param name the name
   * @param sameRootCategories the same root categories
   */
  public static void validateEqual(LocalizedString name, List<Category> sameRootCategories) {
    List<LocalizedStringValue> categoryNames =
        CategoryUtils.getAllCategoryNames(sameRootCategories);
    Map<String, String> localizedName = name.getLocalized();
    for (Map.Entry entry : localizedName.entrySet()) {
      String key = entry.getKey().toString();
      String value = entry.getValue().toString();
      for (LocalizedStringValue categoryName : categoryNames) {
        if (key.equals(categoryName.getLanguage()) && value.equals(categoryName.getText())) {
          LOG.debug("can not create category with same name : {}, key: {}", value, key);
          throw new AlreadyExistException("Can not create category with same name");
        }
      }
    }
  }
}
