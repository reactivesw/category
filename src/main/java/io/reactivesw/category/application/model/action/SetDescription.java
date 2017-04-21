package io.reactivesw.category.application.model.action;

import io.reactivesw.category.infrastructure.update.UpdateAction;
import io.reactivesw.category.infrastructure.util.CategoryActionUtils;
import io.reactivesw.model.LocalizedString;

import lombok.Getter;
import lombok.Setter;

/**
 * SetDescription is used to update category.
 */
@Getter
@Setter
public class SetDescription implements UpdateAction {

  /**
   * If the description parameter is not included, the field will be emptied.
   */
  private LocalizedString description;

  /**
   * get update service name.
   *
   * @return update service name
   */
  @Override
  public String getActionName() {
    return CategoryActionUtils.SET_DESCRIPTION;
  }
}