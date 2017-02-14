package io.reactivesw.category.application.model.action;

import io.reactivesw.category.infrastructure.util.UpdateAction;
import io.reactivesw.model.LocalizedString;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Bruce on 16/12/7.
 */
@Getter
@Setter
public class SetLocalizedDescription implements UpdateAction {
  /**
   * If the description parameter is not included, the field will be emptied.
   */
  private LocalizedString description;

  @Override
  public String getActionName() {
    return null;
  }
}
