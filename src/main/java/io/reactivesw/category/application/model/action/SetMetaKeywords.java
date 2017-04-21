package io.reactivesw.category.application.model.action;

import io.reactivesw.category.infrastructure.update.UpdateAction;
import io.reactivesw.category.infrastructure.util.CategoryActionUtils;
import io.reactivesw.model.LocalizedString;

import lombok.Getter;
import lombok.Setter;

/**
 * SetMetaKeywords is used to update category.
 */
@Getter
@Setter
public class SetMetaKeywords implements UpdateAction {

  /**
   * The Meta keywords.
   */
  private LocalizedString metaKeywords;

  /**
   * Get update service name.
   *
   * @return update service name
   */
  @Override
  public String getActionName() {
    return CategoryActionUtils.SET_META_KEYWORD;
  }
}
