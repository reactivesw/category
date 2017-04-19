package io.reactivesw.category.infrastructure.validator;

import io.reactivesw.category.domain.model.Category;
import io.reactivesw.exception.NotExistException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Validator for parentCategory.
 */
public final class ParentCategoryValidator {

  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(ParentCategoryValidator.class);

  /**
   * Instantiates a new Parent category validator.
   */
  private ParentCategoryValidator() {
  }

  /**
   * validate if parent is null.
   *
   * @param parentId parent id
   * @param parent parent category
   */
  public static void validate(String parentId, Category parent) {
    if (parent == null) {
      LOG.debug("Can not find parent category by id: {}.", parentId);
      throw new NotExistException("Can not find parent category by id: " + parentId);
    }
  }
}
