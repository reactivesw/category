package io.reactivesw.category.domain.service.update;

import io.reactivesw.category.application.model.action.SetOrderHint;
import io.reactivesw.category.domain.model.Category;
import io.reactivesw.category.infrastructure.util.CategoryActionUtils;
import io.reactivesw.category.infrastructure.util.UpdateAction;
import io.reactivesw.category.infrastructure.util.Updater;
import org.springframework.stereotype.Service;

/**
 * Created by Davis on 16/12/29.
 */
@Service(value = CategoryActionUtils.SET_ORDER_HINT)
public class SetOrderHintService extends Updater {
  /**
   * set order hint.
   * @param entity E
   * @param action UpdateAction
   */
  @Override
  public void handle(Category entity, UpdateAction action) {
    SetOrderHint setOrderHint = (SetOrderHint) action;
    entity.setOrderHint(setOrderHint.getOrderHint());
  }
}
