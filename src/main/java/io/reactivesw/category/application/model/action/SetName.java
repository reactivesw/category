package io.reactivesw.category.application.model.action;

import io.reactivesw.category.infrastructure.util.CategoryActionUtils;
import io.reactivesw.category.infrastructure.update.UpdateAction;
import io.reactivesw.model.LocalizedString;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Davis on 16/12/29.
 */
@Getter
@Setter
public class SetName implements UpdateAction {
  /**
   * name.
   */
  @NotNull
  @Size(min = 1, max = 256)
  private LocalizedString name;

  /**
   * get update service name.
   * @return update service name
   */
  @Override
  public String getActionName() {
    return CategoryActionUtils.SET_NAME;
  }
}