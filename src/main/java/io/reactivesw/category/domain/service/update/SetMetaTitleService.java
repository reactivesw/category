package io.reactivesw.category.domain.service.update;

import io.reactivesw.category.application.model.action.SetMetaTitle;
import io.reactivesw.category.application.model.mapper.LocalizedStringMapper;
import io.reactivesw.category.domain.model.Category;
import io.reactivesw.category.infrastructure.update.UpdateAction;
import io.reactivesw.category.infrastructure.util.CategoryActionUtils;
import io.reactivesw.model.Updater;
import org.springframework.stereotype.Service;

/**
 * Service to set metaTitle when update category.
 */
@Service(value = CategoryActionUtils.SET_META_TITLE)
public class SetMetaTitleService implements Updater<Category, UpdateAction> {

  /**
   * Set meta title.
   *
   * @param entity E
   * @param action UpdateAction
   */
  @Override
  public void handle(Category entity, UpdateAction action) {
    SetMetaTitle setMetaTitle = (SetMetaTitle) action;
    entity.setMetaTitle(LocalizedStringMapper.toEntityDefaultNew(setMetaTitle.getMetaTitle()));
  }
}
