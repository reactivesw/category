package io.reactivesw.category.domain.service.update;

import io.reactivesw.category.application.model.action.SetMetaDescription;
import io.reactivesw.category.application.model.mapper.LocalizedStringMapper;
import io.reactivesw.category.domain.model.Category;
import io.reactivesw.category.infrastructure.update.UpdateAction;
import io.reactivesw.category.infrastructure.util.CategoryActionUtils;
import io.reactivesw.model.Updater;
import org.springframework.stereotype.Service;

/**
 * Service to set metaDescription when update category.
 */
@Service(value = CategoryActionUtils.SET_META_DESCRIPTION)
public class SetMetaDescriptionService implements Updater<Category, UpdateAction> {
  /**
   * Set meta description.
   * @param entity E
   * @param action UpdateAction
   */
  @Override
  public void handle(Category entity, UpdateAction action) {
    SetMetaDescription setMetaDescription = (SetMetaDescription) action;
    entity.setMetaDescription(LocalizedStringMapper.toEntityDefaultNew(setMetaDescription
        .getMetaDescription()));
  }
}
