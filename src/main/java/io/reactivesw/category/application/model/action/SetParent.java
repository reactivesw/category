package io.reactivesw.category.application.model.action;

import io.reactivesw.category.infrastructure.update.UpdateAction;
import io.reactivesw.category.infrastructure.util.CategoryActionUtils;
import io.reactivesw.model.Reference;

import lombok.Getter;
import lombok.Setter;

/**
 * SetParent for updating category.
 */
@Getter
@Setter
public class SetParent implements UpdateAction {
  /**
   * The Parent.
   */
  private Reference parent;

  /**
   * get update service name.
   *
   * @return update service name
   */
  @Override
  public String getActionName() {
    return CategoryActionUtils.SET_PARENT;
  }
}
