package io.reactivesw.category.application.model.action;

import io.reactivesw.category.infrastructure.update.UpdateAction;
import io.reactivesw.category.infrastructure.util.CategoryActionUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * SetExternaliId is used to update category.
 */
@Getter
@Setter
public class SetExternalId implements UpdateAction {

  /**
   * The External id.
   */
  private String externalId;

  /**
   * Get update service name.
   *
   * @return update service name
   */
  @Override
  public String getActionName() {
    return CategoryActionUtils.SET_EXTERNAL_ID;
  }
}
