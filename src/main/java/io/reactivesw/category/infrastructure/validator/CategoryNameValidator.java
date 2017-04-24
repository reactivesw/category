package io.reactivesw.category.infrastructure.validator;

import io.reactivesw.category.application.model.CategoryDraft;
import io.reactivesw.category.domain.model.Category;
import io.reactivesw.category.domain.model.LocalizedStringValue;
import io.reactivesw.category.infrastructure.util.CategoryUtils;
import io.reactivesw.exception.AlreadyExistException;
import io.reactivesw.exception.ParametersException;
import io.reactivesw.model.LocalizedString;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Validator for category name.
 */
public final class CategoryNameValidator {

  /**
   * Logger.
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
      LOG.debug("CategoryView name can not be null.");
      throw new ParametersException("CategoryView name can not be null.");
    }
    Predicate<Map.Entry<String, String>> entryPredicate =
        (entry) -> StringUtils.isEmpty(entry.getValue()) || StringUtils.isEmpty(entry.getKey());
    if (draft.getName().getLocalized().entrySet().stream()
        .anyMatch(entryPredicate)) {
      LOG.debug("CategoryView name can not be empty.");
      throw new ParametersException("CategoryView name can not be empty.");
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

    name.getLocalized().entrySet().stream().forEach(x -> {
      if (categoryNames.stream().anyMatch((categoryName) ->
          x.getKey().toString().equals(categoryName.getLanguage()) &&
              x.getValue().toString().equals(categoryName.getText()))) {
        LOG.debug("Can not create category with same name: {}, key: {}.", x.getValue(), x.getKey());
        throw new AlreadyExistException("Can not create category with same name.");
      }
    });
  }
}
