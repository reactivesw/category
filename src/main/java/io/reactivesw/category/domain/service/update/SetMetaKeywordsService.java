package io.reactivesw.category.domain.service.update;

import io.reactivesw.category.application.model.action.SetMetaKeywords;
import io.reactivesw.category.application.model.mapper.LocalizedStringMapper;
import io.reactivesw.category.domain.model.Category;
import io.reactivesw.category.infrastructure.util.CategoryActionUtils;
import io.reactivesw.category.infrastructure.util.UpdateAction;
import io.reactivesw.category.infrastructure.util.Updater;
import org.springframework.stereotype.Service;

/**
 * Created by Davis on 16/12/29.
 */
@Service(value = CategoryActionUtils.SET_META_KEYWORD)
public class SetMetaKeywordsService extends Updater {

  /**
   * set meta key words.
   * @param entity E
   * @param action UpdateAction
   */
  @Override
  public void handle(Category entity, UpdateAction action) {
    SetMetaKeywords setMetaKeywords = (SetMetaKeywords) action;
    entity.setMetaKeyWords(LocalizedStringMapper.modelToEntityDefaultNew(setMetaKeywords
        .getMetaKeywords()));
  }
}
