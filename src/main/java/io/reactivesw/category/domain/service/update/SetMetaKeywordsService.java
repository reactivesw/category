package io.reactivesw.category.domain.service.update;

import io.reactivesw.category.application.model.action.SetMetaKeywords;
import io.reactivesw.category.application.model.mapper.LocalizedStringMapper;
import io.reactivesw.category.domain.model.Category;
import io.reactivesw.category.infrastructure.update.UpdateAction;
import io.reactivesw.category.infrastructure.util.CategoryActionUtils;
import io.reactivesw.model.Updater;
import org.springframework.stereotype.Service;

/**
 * Service to set metaKeywords when update category.
 */
@Service(value = CategoryActionUtils.SET_META_KEYWORD)
public class SetMetaKeywordsService implements Updater<Category, UpdateAction> {

  /**
   * Set meta key words.
   *
   * @param entity E
   * @param action UpdateAction
   */
  @Override
  public void handle(Category entity, UpdateAction action) {
    SetMetaKeywords setMetaKeywords = (SetMetaKeywords) action;
    entity.setMetaKeyWords(LocalizedStringMapper.toEntityDefaultNew(setMetaKeywords
        .getMetaKeywords()));
  }
}
