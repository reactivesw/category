package io.reactivesw.category.application.model.action;

import io.reactivesw.category.infrastructure.util.CategoryActionUtils;
import io.reactivesw.category.infrastructure.util.UpdateAction;
import io.reactivesw.model.LocalizedString;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Davis on 16/12/29.
 */
@Getter
@Setter
public class SetName implements UpdateAction {
  /**
   * name.
   */
  private LocalizedString name;

  @Override
  public String getActionName() {
    return CategoryActionUtils.SET_NAME;
  }
}