package io.reactivesw.category.application.model.action;

import io.reactivesw.category.infrastructure.update.UpdateAction;
import io.reactivesw.model.LocalizedString;
import lombok.Data;

/**
 * Created by Davis on 16/12/6.
 */
@Data
public class SetLocalizedName implements UpdateAction {
  /**
   * name.
   */
  private LocalizedString name;

  /**
   * get update service name.
   * @return update service name
   */
  @Override
  public String getActionName() {
    return null;
  }
}
