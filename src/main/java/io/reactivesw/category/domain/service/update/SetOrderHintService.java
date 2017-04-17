package io.reactivesw.category.domain.service.update;

import io.reactivesw.category.application.model.action.SetOrderHint;
import io.reactivesw.category.domain.model.Category;
import io.reactivesw.category.infrastructure.update.UpdateAction;
import io.reactivesw.category.infrastructure.util.CategoryActionUtils;
import io.reactivesw.category.infrastructure.validator.CategoryOrderHintValidator;
import io.reactivesw.model.Updater;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service(value = CategoryActionUtils.SET_ORDER_HINT)
public class SetOrderHintService implements Updater<Category, UpdateAction> {
  /**
   * Set order hint.
   * @param entity E
   * @param action UpdateAction
   */
  @Override
  public void handle(Category entity, UpdateAction action) {
    SetOrderHint setOrderHint = (SetOrderHint) action;
    CategoryOrderHintValidator.ValiddateValid(setOrderHint);
    entity.setOrderHint(calculateMedianOfOrderHint(setOrderHint.getPreviousOrderHint(),
        setOrderHint.getNextOrderHint()));
  }

  /**
   * Calculate median of two order hint
   * @param previousOrderHint previousOrderHint
   * @param nextOrderHint nextOrderHint
   * @return median of two order hint
   */
  private String calculateMedianOfOrderHint(String previousOrderHint, String nextOrderHint) {
    double previous = Double.parseDouble(previousOrderHint);
    double next = Double.parseDouble(nextOrderHint);
    return String.valueOf((previous + next) / 2);
  }
}
