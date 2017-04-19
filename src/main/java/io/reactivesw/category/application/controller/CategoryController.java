package io.reactivesw.category.application.controller;

import io.reactivesw.category.application.model.CategoryDraft;
import io.reactivesw.category.application.model.CategoryView;
import io.reactivesw.category.application.model.PagedQueryResult;
import io.reactivesw.category.application.model.QueryConditions;
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
 * Created by Davis on 16/11/18.
 */
@RestController
public class CategoryController {

  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(CategoryController.class);

  /**
   * CategoryEntity Service.
   */
  @Autowired
  private transient CategoryService categoryService;

  /**
   * Create category category.
   *
   * @param categoryDraft the draft
   * @return the category
   */
  @PostMapping(Router.CATEGORY_ROOT)
  public CategoryView createCategory(@RequestBody
  @Valid CategoryDraft categoryDraft) {
    LOG.debug("Create category: {}.", categoryDraft.toString());

    CategoryNameValidator.validateNull(categoryDraft);

    CategoryView category = categoryService.createCategory(categoryDraft);

    LOG.debug("End createCategory, saved category is {}.", category.toString());

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
    LOG.debug("Enter deleteCategory, id is {}, version is {}.", id, version);

    categoryService.deleteCategory(id, version);

    LOG.debug("End deleteCategory, id is {}, version is {}.", id, version);
  }

  /**
   * Update category category.
   *
   * @param id the id
   * @param updateRequest the fields
   * @return the category
   */
  @PutMapping(Router.CATEGORY_WITH_ID)
  public CategoryView updateCategory(@PathVariable(value = Router.CATEGORY_ID) String id,
      @RequestBody @Valid UpdateRequest updateRequest) {
    LOG.debug("Enter updateCategory,id is {}, update request is {}.", id, updateRequest.toString());

    CategoryView result = categoryService.updateCategory(id, updateRequest.getVersion(),
        updateRequest.getActions());

    LOG.debug("End updateCategory, updated Category is {}.", result.toString());

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
    LOG.debug("Enter getCategoryById, id is {}.", id);
    CategoryView category = categoryService.getCategoryById(id);
    LOG.debug("End getCategoryById, get category: {}.", category.toString());
    return category;
  }

  /**
   * Query category list.
   *
   * @return the list
   */
  @GetMapping(Router.CATEGORY_ROOT)
  public PagedQueryResult<CategoryView> queryCategories(QueryConditions query) {
    LOG.debug("Enter queryCategories, query parameters: {}.", query.toString());

    PagedQueryResult<CategoryView> result = categoryService.queryCategories(query);

    LOG.debug("End queryCategories, query result is: {}.", result.toString());

    return result;
  }
}