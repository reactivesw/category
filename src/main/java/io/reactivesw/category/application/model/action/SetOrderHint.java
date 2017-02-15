package io.reactivesw.category.application.model.action;

import io.reactivesw.category.infrastructure.util.CategoryActionUtils;
import io.reactivesw.category.infrastructure.update.UpdateAction;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Davis on 16/12/6.
 */
@Getter
@Setter
public class SetOrderHint implements UpdateAction {
  /**
   * The Order hint.
   */
  private String orderHint;

  @Override
  public String getActionName() {
    return CategoryActionUtils.SET_ORDER_HINT;
  }
}
