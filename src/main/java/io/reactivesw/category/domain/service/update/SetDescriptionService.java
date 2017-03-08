package io.reactivesw.category.domain.service.update;

import io.reactivesw.category.application.model.action.SetDescription;
import io.reactivesw.category.application.model.mapper.LocalizedStringMapper;
import io.reactivesw.category.domain.model.Category;
import io.reactivesw.category.infrastructure.update.UpdateAction;
import io.reactivesw.category.infrastructure.util.CategoryActionUtils;
import io.reactivesw.model.Updater;

import org.springframework.stereotype.Service;

/**
 * Created by Davis on 16/12/29.
 */
@Service(value = CategoryActionUtils.SET_DESCRIPTION)
public class SetDescriptionService implements Updater<Category, UpdateAction> {
  /**
   * set description.
   * @param entity E
   * @param action UpdateAction
   */
  @Override
  public void handle(Category entity, UpdateAction action) {
    SetDescription setLocalizedDescription = (SetDescription) action;
    entity.setDescription(LocalizedStringMapper.modelToEntityDefaultNew(setLocalizedDescription
        .getDescription()));
  }
}
