package io.reactivesw.category.application.model.action;

import io.reactivesw.category.infrastructure.update.UpdateAction;
import io.reactivesw.category.infrastructure.util.CategoryActionUtils;
import io.reactivesw.model.LocalizedString;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * SetName is used to update category.
 */
@Getter
@Setter
public class SetName implements UpdateAction {

  /**
   * Name.
   */
  @NotNull
  private LocalizedString name;

  /**
   * Get update service name.
   *
   * @return update service name
   */
  @Override
  public String getActionName() {
    return CategoryActionUtils.SET_NAME;
  }
}