package io.reactivesw.category.application.model.action;

import io.reactivesw.category.infrastructure.util.CategoryActionUtils;
import io.reactivesw.category.infrastructure.update.UpdateAction;
import io.reactivesw.model.LocalizedString;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Davis on 16/12/6.
 */
@Getter
@Setter
public class SetMetaDescription implements UpdateAction {
  /**
   * The Meta description.
   */
  private LocalizedString metaDescription;

  @Override
  public String getActionName() {
    return CategoryActionUtils.SET_META_DESCRIPTION;
  }
}
