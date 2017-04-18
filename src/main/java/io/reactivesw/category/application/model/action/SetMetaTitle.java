package io.reactivesw.category.application.model.action;

import io.reactivesw.category.infrastructure.update.UpdateAction;
import io.reactivesw.category.infrastructure.util.CategoryActionUtils;
import io.reactivesw.model.LocalizedString;

import lombok.Getter;
import lombok.Setter;

/**
 * SetMetaTitle for updating category.
 */
@Getter
@Setter
public class SetMetaTitle implements UpdateAction {
  /**
   * The Meta title.
   */
  private LocalizedString metaTitle;

  /**
   * get update service name.
   * @return update service name
   */
  @Override
  public String getActionName() {
    return CategoryActionUtils.SET_META_TITLE;
  }
}
