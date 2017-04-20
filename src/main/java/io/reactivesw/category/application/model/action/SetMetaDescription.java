package io.reactivesw.category.application.model.action;

import io.reactivesw.category.infrastructure.update.UpdateAction;
import io.reactivesw.category.infrastructure.util.CategoryActionUtils;
import io.reactivesw.model.LocalizedString;

import lombok.Getter;
import lombok.Setter;

/**
 * SetMetaDescription for updating category.
 */
@Getter
@Setter
public class SetMetaDescription implements UpdateAction {
  /**
   * The Meta description.
   */
  private LocalizedString metaDescription;

  /**
   * get update service name.
   *
   * @return update service name
   */
  @Override
  public String getActionName() {
    return CategoryActionUtils.SET_META_DESCRIPTION;
  }
}
