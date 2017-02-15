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
public class SetMetaKeywords implements UpdateAction {
  /**
   * The Meta keywords.
   */
  private LocalizedString metaKeywords;

  @Override
  public String getActionName() {
    return CategoryActionUtils.SET_META_KEYWORD;
  }
}
