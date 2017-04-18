package io.reactivesw.category.application.model.action;

import io.reactivesw.category.infrastructure.update.UpdateAction;
import io.reactivesw.category.infrastructure.util.CategoryActionUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * SetExternaliId for updating category.
 */
@Getter
@Setter
public class SetExternalId implements UpdateAction {
  /**
   * The External id.
   */
  private String externalId;

  /**
   * get update service name.
   *
   * @return update service name
   */
  @Override
  public String getActionName() {
    return CategoryActionUtils.SET_EXTERNAL_ID;
  }
}
