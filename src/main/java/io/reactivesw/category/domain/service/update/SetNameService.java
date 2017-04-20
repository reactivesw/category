package io.reactivesw.category.domain.service.update;

import io.reactivesw.category.application.model.action.SetName;
import io.reactivesw.category.application.model.mapper.LocalizedStringMapper;
import io.reactivesw.category.domain.model.Category;
import io.reactivesw.category.infrastructure.update.UpdateAction;
import io.reactivesw.category.infrastructure.util.CategoryActionUtils;
import io.reactivesw.model.Updater;
import org.springframework.stereotype.Service;

/**
 * Service to set name when update category.
 */
@Service(value = CategoryActionUtils.SET_NAME)
public class SetNameService implements Updater<Category, UpdateAction> {

  /**
   * set name.
   *
   * @param entity E
   * @param action UpdateAction
   */
  @Override
  public void handle(Category entity, UpdateAction action) {
    SetName setLocalizedName = (SetName) action;
    entity.setName(LocalizedStringMapper.toEntityDefaultNew(setLocalizedName.getName()));
  }
}
