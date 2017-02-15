package io.reactivesw.category.domain.service.update;

import io.reactivesw.category.application.model.action.SetParent;
import io.reactivesw.category.domain.model.Category;
import io.reactivesw.category.domain.service.CategoryService;
import io.reactivesw.category.infrastructure.update.UpdateAction;
import io.reactivesw.category.infrastructure.util.CategoryActionUtils;
import io.reactivesw.model.Updater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Davis on 16/12/29.
 */
@Service(value = CategoryActionUtils.SET_PARENT)
public class SetParentService implements Updater<Category, UpdateAction> {

  /**
   * category service.
   */
  @Autowired
  private transient CategoryService categoryService;

  /**
   * set parent.
   *
   * @param entity E
   * @param action UpdateAction
   */
  @Override
  public void handle(Category entity, UpdateAction action) {
    SetParent setParent = (SetParent) action;
    categoryService.setParentAndAncestors(entity, setParent.getParent().getId());
    // TODO: 16/12/28 should change ancestors and subCategory's ancestors
  }
}
