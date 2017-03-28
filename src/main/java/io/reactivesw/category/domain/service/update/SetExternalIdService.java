package io.reactivesw.category.domain.service.update;

import io.reactivesw.category.application.model.action.SetExternalId;
import io.reactivesw.category.domain.model.Category;
import io.reactivesw.category.infrastructure.update.UpdateAction;
import io.reactivesw.category.infrastructure.util.CategoryActionUtils;
import io.reactivesw.model.Updater;
import org.springframework.stereotype.Service;

/**
 * Created by Davis on 16/12/29.
 */
@Service(value = CategoryActionUtils.SET_EXTERNAL_ID)
public class SetExternalIdService implements Updater<Category, UpdateAction> {

  /**
   * set external id.
   *
   * @param entity E
   * @param action UpdateAction
   */
  @Override
  public void handle(Category entity, UpdateAction action) {
    SetExternalId setExternalId = (SetExternalId) action;
    entity.setExternalId(setExternalId.getExternalId());
  }
}
