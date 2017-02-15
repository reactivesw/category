package io.reactivesw.category.application.model.action;

import io.reactivesw.category.infrastructure.util.CategoryActionUtils;
import io.reactivesw.category.infrastructure.update.UpdateAction;
import io.reactivesw.model.LocalizedString;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Davis on 16/12/29.
 */
@Getter
@Setter
public class SetDescription implements UpdateAction {
  /**
   * If the description parameter is not included, the field will be emptied.
   */
  private LocalizedString description;

  @Override
  public String getActionName() {
    return CategoryActionUtils.SET_DESCRIPTION;
  }
}