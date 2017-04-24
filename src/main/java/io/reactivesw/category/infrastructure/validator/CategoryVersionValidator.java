package io.reactivesw.category.infrastructure.validator;

import io.reactivesw.category.domain.model.Category;
import io.reactivesw.exception.ConflictException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * Validator for category version.
 */
public final class CategoryVersionValidator {

  /**
   * Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(CategoryVersionValidator.class);

  /**
   * Instantiates a new CategoryView version validator.
   */
  private CategoryVersionValidator() {
  }

  /**
   * Judge entity and version.
   *
   * @param entity the CategoryEntity
   * @param version the version
   * @throws ConflictException when version not match
   */
  public static void validate(Category entity, Integer version) {
    if (!Objects.equals(version, entity.getVersion())) {
      LOG.debug("Version not match, input version: {}, entity version: {}.",
          version, entity.getVersion());
      throw new ConflictException("Version not match.");
    }
  }
}
