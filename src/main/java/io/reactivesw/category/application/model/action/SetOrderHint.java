package io.reactivesw.category.application.model.action;

import io.reactivesw.category.infrastructure.update.UpdateAction;
import io.reactivesw.category.infrastructure.util.CategoryActionUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Davis on 16/12/6.
 */
@Getter
@Setter
public class SetOrderHint implements UpdateAction {
  /**
   * The previous order hint.
   */
  private String previousOrderHint;

  /**
   * The next order hint
   */
  private String nextOrderHint;

  /**
   * Get update service name.
   *
   * @return update service name
   */
  @Override
  public String getActionName() {
    return CategoryActionUtils.SET_ORDER_HINT;
  }
}
