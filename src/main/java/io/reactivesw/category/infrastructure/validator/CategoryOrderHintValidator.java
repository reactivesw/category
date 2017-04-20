package io.reactivesw.category.infrastructure.validator;

import io.reactivesw.category.application.model.action.SetOrderHint;
import io.reactivesw.exception.ParametersException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Validator for order hint.
 */
public final class CategoryOrderHintValidator {


  /**
   * Logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(CategoryOrderHintValidator.class);

  /**
   * Instantiates a new CategoryOrderHint validator.
   */
  private CategoryOrderHintValidator() {

  }

  /**
   * Validate orderHint.
   *
   * @param setOrderHint setOrderHint
   */
  public static void validateEmptyAndNumeric(SetOrderHint setOrderHint) {
    if (StringUtils.isEmpty(setOrderHint.getPreviousOrderHint()) || !NumberUtils
        .isNumber(setOrderHint.getPreviousOrderHint())) {
      LOGGER.debug("Previous order hint  must be number.");
      throw new ParametersException("Previous order hint must be number");
    }
    if (!StringUtils.isEmpty(setOrderHint.getNextOrderHint()) && !NumberUtils
        .isNumber(setOrderHint.getNextOrderHint())) {
      LOGGER.debug("Next order hint  must be number.");
      throw new ParametersException("Next order hint must be number");
    }
  }
}
