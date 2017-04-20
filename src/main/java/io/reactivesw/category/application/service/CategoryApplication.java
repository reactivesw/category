package io.reactivesw.category.application.service;

import io.reactivesw.category.domain.service.CategoryService;
import io.reactivesw.category.domain.service.EventMessageService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Category Application.
 */
@Service
public class CategoryApplication {

  /**
   * logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(CategoryApplication.class);

  /**
   * Category Service.
   */
  private transient CategoryService categoryService;

  /**
   * Event Message Service.
   */
  private transient EventMessageService eventMessageService;

  /**
   * Instantiates a new Category application.
   *
   * @param categoryService the category service
   * @param eventMessageService the event message service
   */
  @Autowired
  public CategoryApplication(CategoryService categoryService, EventMessageService
      eventMessageService) {
    this.categoryService = categoryService;
    this.eventMessageService = eventMessageService;
  }

  /**
   * Delete category by id and version.
   *
   * @param id the id
   * @param version the version
   */
  @Transactional
  public void deleteCategory(String id, Integer version) {
    LOG.debug("Enter. CategoryId: {}, version: {}.", id, version);

    List<String> idList = categoryService.deleteCategory(id, version);

    eventMessageService.saveDeletedEvent(idList);

    LOG.debug("Exit.");
  }
}