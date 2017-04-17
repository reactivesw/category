package io.reactivesw.category.infrastructure.validator;

import io.reactivesw.category.application.model.action.SetOrderHint;
import io.reactivesw.category.infrastructure.update.UpdateAction;
import io.reactivesw.exception.ParametersException;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * valid order hint of category
 */
public class CategoryOrderHintValidator {
  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(CategoryOrderHintValidator.class);

  /**
   * Check whether order hint is a number and between 0 and 1
   * @param updateAction updateAction
   */
  public static void ValiddateValid(UpdateAction updateAction) {
    SetOrderHint setOrderHint = (SetOrderHint) updateAction;
    if (!NumberUtils.isNumber(setOrderHint.getPreviousOrderHint()) || !NumberUtils
        .isNumber(setOrderHint.getNextOrderHint())) {
      LOG.debug("order hint must be numeric");
      throw new ParametersException("order hint must be numeric");
    }
    Double previousOrderHint = Double.parseDouble(setOrderHint.getPreviousOrderHint());
    Double nextOrderHint = Double.parseDouble(setOrderHint.getNextOrderHint());
    if (!numberBetween0And1(previousOrderHint) || !numberBetween0And1(nextOrderHint)) {
      LOG.debug("order hint must between 0 and 1");
      throw new ParametersException("order hint must between 0 and 1");
    }
  }

  /**
   * check whether order hint is between 0 and 1
   * @param number order hint
   * @return the result of comparison
   */
  private static boolean numberBetween0And1(Double number) {
    return (number > 0) && (number < 1);
  }
}
