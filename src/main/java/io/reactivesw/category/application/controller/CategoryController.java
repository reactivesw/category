package io.reactivesw.category.application.controller;

import io.reactivesw.category.application.model.CategoryDraft;
import io.reactivesw.category.application.model.CategoryView;
import io.reactivesw.category.application.model.PagedQueryResult;
import io.reactivesw.category.application.model.QueryConditions;
import io.reactivesw.category.application.service.CategoryApplication;
import io.reactivesw.category.domain.service.CategoryService;
import io.reactivesw.category.infrastructure.Router;
import io.reactivesw.category.infrastructure.update.UpdateRequest;
import io.reactivesw.category.infrastructure.validator.CategoryNameValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * Category controller.
 */
@RestController
public class CategoryController {

  /**
   * Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(CategoryController.class);

  /**
   * Category Service.
   */
  private transient CategoryService categoryService;

  /**
   * Category Application.
   */
  private transient CategoryApplication categoryApplication;

  /**
   * Instantiates a new Category controller.
   *
   * @param categoryService the category service
   * @param categoryApplication the category application
   */
  @Autowired
  public CategoryController(CategoryService categoryService,
      CategoryApplication categoryApplication) {
    this.categoryService = categoryService;
    this.categoryApplication = categoryApplication;
  }

  /**
   * Create category.
   *
   * @param categoryDraft the draft
   * @return the category
   */
  @PostMapping(Router.CATEGORY_ROOT)
  public CategoryView createCategory(@RequestBody @Valid CategoryDraft categoryDraft) {

    LOG.info("Enter. Create category: {}.", categoryDraft);

    CategoryNameValidator.validateNull(categoryDraft);

    CategoryView category = categoryService.createCategory(categoryDraft);

    LOG.info("Exit. CategoryId {}.", category.getId());
    LOG.trace("Category: {}.", category);

    return category;
  }

  /**
   * Delete category.
   *
   * @param version the version
   */
  @DeleteMapping(value = Router.CATEGORY_WITH_ID)
  public void deleteCategory(@PathVariable(value = Router.CATEGORY_ID) String id,
      @RequestParam Integer version) {
    LOG.info("Enter. CategoryId: {}, version: {}.", id, version);

    categoryApplication.deleteCategory(id, version);

    LOG.info("Exit. CategoryId: {}, version: {}.", id, version);
  }

  /**
   * Update category.
   *
   * @param id the id
   * @param updateRequest the fields
   * @return the category
   */
  @PutMapping(Router.CATEGORY_WITH_ID)
  public CategoryView updateCategory(@PathVariable(value = Router.CATEGORY_ID) String id,
      @RequestBody @Valid UpdateRequest updateRequest) {
    LOG.info("Enter. CategoryId: {}, update request: {}.", id, updateRequest);

    CategoryView result = categoryService.updateCategory(id, updateRequest.getVersion(),
        updateRequest.getActions());

    LOG.info("Exit. CategoryId: {}.", result.getId());
    LOG.trace("Updated category: {}.", result);

    return result;
  }

  /**
   * Gets category by id.
   *
   * @param id the id
   * @return the category by id
   */
  @GetMapping(Router.CATEGORY_WITH_ID)
  public CategoryView getCategoryById(@PathVariable(value = Router.CATEGORY_ID) String id) {
    LOG.info("Enter. CategoryId: {}.", id);
    CategoryView category = categoryService.getCategoryById(id);
    LOG.info("Exit. CategoryId: {}.", category.getId());
    LOG.trace("Category: {}.", category);
    return category;
  }

  /**
   * Query category list.
   *
   * @return the list
   */
  @GetMapping(Router.CATEGORY_ROOT)
  public PagedQueryResult<CategoryView> queryCategories(QueryConditions query) {
    LOG.info("Enter. Query parameters: {}.", query);
    PagedQueryResult<CategoryView> result = categoryService.queryCategories(query);

    LOG.info("Exit. Result count: {}.", result.getCount());
    LOG.trace("Query result: {}.", result);

    return result;
  }
}