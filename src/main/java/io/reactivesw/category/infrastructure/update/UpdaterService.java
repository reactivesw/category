package io.reactivesw.category.infrastructure.update;

import io.reactivesw.category.domain.model.Category;
import io.reactivesw.model.Updater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * we may got two kind of update: just use the data in action, or still use data from other service.
 * if we just use the data in action, we can only use action mapper to set the data.
 * if we need get data from other palace, we should use update service.
 */
@Service
public class UpdaterService implements Updater<Category, UpdateAction> {

  /**
   * ApplicationContext for get update services.
   */
  @Autowired
  private transient ApplicationContext context;

  /**
   * Put the value in action to entity.
   *
   * @param entity E
   * @param action UpdateAction
   */
  @Override
  public void handle(Category entity, UpdateAction action) {
    Updater updater = getUpdateService(action);
    updater.handle(entity, action);
  }

  /**
   * Get mapper.
   *
   * @param action UpdateAction
   * @return ZoneUpdateMapper
   */
  private Updater getUpdateService(UpdateAction action) {
    return (Updater) context.getBean(action.getActionName());
  }
}
