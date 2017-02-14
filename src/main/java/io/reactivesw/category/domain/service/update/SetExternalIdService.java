package io.reactivesw.category.domain.service.update;

import io.reactivesw.category.application.model.action.SetExternalID;
import io.reactivesw.category.domain.model.Category;
import io.reactivesw.category.infrastructure.util.CategoryActionUtils;
import io.reactivesw.category.infrastructure.util.UpdateAction;
import io.reactivesw.category.infrastructure.util.Updater;
import org.springframework.stereotype.Service;

/**
 * Created by Davis on 16/12/29.
 */
@Service(value = CategoryActionUtils.SET_EXTERNAL_ID)
public class SetExternalIdService extends Updater {

  /**
   * set external id.
   * @param entity E
   * @param action UpdateAction
   */
  @Override
  public void handle(Category entity, UpdateAction action) {
    SetExternalID setExternalID = (SetExternalID) action;
    entity.setExternalId(setExternalID.getExternalId());
  }
}
