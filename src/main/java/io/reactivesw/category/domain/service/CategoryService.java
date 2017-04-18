package io.reactivesw.category.domain.service;

import com.google.common.collect.Lists;

import io.reactivesw.category.application.model.CategoryDraft;
import io.reactivesw.category.application.model.CategoryView;
import io.reactivesw.category.application.model.PagedQueryResult;
import io.reactivesw.category.application.model.QueryConditions;
import io.reactivesw.category.application.model.mapper.CategoryMapper;
import io.reactivesw.category.domain.model.Category;
import io.reactivesw.category.infrastructure.repository.CategoryRepository;
import io.reactivesw.category.infrastructure.update.UpdateAction;
import io.reactivesw.category.infrastructure.update.UpdaterService;
import io.reactivesw.category.infrastructure.validator.CategoryNameValidator;
import io.reactivesw.category.infrastructure.validator.CategoryVersionValidator;
import io.reactivesw.category.infrastructure.validator.ParentCategoryValidator;
import io.reactivesw.exception.AlreadyExistException;
import io.reactivesw.exception.NotExistException;
import io.reactivesw.model.Reference;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Davis on 16/11/28.
 */
@Service
public class CategoryService {
  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(CategoryService.class);

  /**
   * CategoryEntity Repository.
   */
  @Autowired
  private transient CategoryRepository categoryRepository;

  /**
   * category update service.
   */
  @Autowired
  private transient UpdaterService updateService;

  /**
   * Create category.
   *
   * @param categoryDraft the category draft
   * @return the category
   */
  public CategoryView createCategory(CategoryDraft categoryDraft) {
    LOG.debug("Enter createCategory, CategoryDraft is {}.", categoryDraft.toString());

    String parentId = getParentId(categoryDraft);
    List<Category> sameRootCategories = categoryRepository.queryCategoryByParent(parentId);
    CategoryNameValidator.validateEqual(categoryDraft.getName(), sameRootCategories);

    Category entity = CategoryMapper.toEntity(categoryDraft);
    setParentAndAncestors(entity, parentId);

    Category savedEntity = saveCategoryEntity(entity);

    CategoryView categoryView = CategoryMapper.toModel(savedEntity);
    LOG.debug("End createCategory, new CategoryEntity is: {}.", categoryView.toString());
    //TODO send message
    return categoryView;
  }

  /**
   * Delete category by id and version.
   *
   * @param id      the id
   * @param version the version
   */
  @Transactional
  public void deleteCategory(String id, Integer version) {
    LOG.debug("Enter deleteCategory, id:{}, version:{}.", id, version);

    Category entity = getById(id);
    CategoryVersionValidator.validate(entity, version);

    List<Category> tatalCategoryEitities = Lists.newArrayList(entity);
    List<Category> subCategories = categoryRepository.querySubCategoriesByAncestorId(id);
    if (subCategories != null && !subCategories.isEmpty()) {
      tatalCategoryEitities.addAll(subCategories);
    }

    categoryRepository.delete(tatalCategoryEitities);

    //TODO send message for:
    // remove from all those product that had that category assigned in their ProductData

    LOG.debug("end deleteCategory, id:{}, version:{}", id, version);
  }

  /**
   * Update category.
   *
   * @param id      the id
   * @param version the update request
   * @param actions the update action
   * @return the category
   */
  public CategoryView updateCategory(String id, Integer version, List<UpdateAction> actions) {
    LOG.debug("Enter updateCategory, id is {}, version is {}, update action is {}.",
        id, version, actions);

    Category entity = getById(id);
    CategoryVersionValidator.validate(entity, version);

    Category updatedEntity = updateCategoryEntity(actions, entity);
    //TODO send message, if slug be updated
    CategoryView result = CategoryMapper.toModel(updatedEntity);

    LOG.debug("End updateCategory, updated Category is {}.", result);
    return result;
  }

  /**
   * Gets category by id.
   *
   * @param id the id
   * @return the category by id
   * @throws NotExistException if the can not find CategoryEntity by the id
   */
  public CategoryView getCategoryById(String id) {
    LOG.debug("Enter getCategoryById, id is {}.", id);

    Category entity = getById(id);

    CategoryView result = CategoryMapper.toModel(entity);

    LOG.debug("End getCategoryById, get category is: {}.", result.toString());

    return result;
  }

  /**
   * Query category.
   * TODO: 16/12/13 queryconditions
   * @param queryConditions the QueryConditions
   * @return the paged query result
   */
  public PagedQueryResult<CategoryView> queryCategories(QueryConditions queryConditions) {
    LOG.debug("Enter queryCategories, QueryConditions is: {}.", queryConditions.toString());

    List<Category> entities = categoryRepository.findAll();

    List<CategoryView> result = CategoryMapper.toModel(entities);

    LOG.debug("End queryCategories, get Categories: {}.", result);

    PagedQueryResult<CategoryView> pagedQueryResult = new PagedQueryResult<>();
    pagedQueryResult.setResults(result);
    pagedQueryResult.setCount(result.size());

    return pagedQueryResult;
  }


  /**
   * gete parent id by CategoryDraft.
   *
   * @param categoryDraft the CategoryDraft
   * @return parent id
   */
  private String getParentId(CategoryDraft categoryDraft) {
    String parentId = "";
    Reference parentReference = categoryDraft.getParent();
    if (parentReference != null && StringUtils.isNotBlank(parentReference.getId())) {
      parentId = parentReference.getId();
    }
    return parentId;
  }

  /**
   * set parent id and ancestors.
   *
   * @param entity   category entity
   * @param parentId parent id
   * @return CategoryEntity
   */
  public Category setParentAndAncestors(Category entity, String parentId) {
    List<String> ancestors = Lists.newArrayList();
    if (StringUtils.isNotBlank(parentId)) {
      Category parent = getParentCategory(parentId);
      ancestors = setAncestors(parentId, parent);
    }
    entity.setParent(parentId);
    entity.setAncestors(ancestors);

    return entity;
  }


  /**
   * Save category entity.
   *
   * @param entity the entity
   * @return the category entity
   * @throws AlreadyExistException if slug is already exist and get DataIntegrityViolationException
   */
  @Transactional
  private Category saveCategoryEntity(Category entity) {
    Category savedEntity = null;
    try {
      savedEntity = categoryRepository.save(entity);
    } catch (DataIntegrityViolationException dataIntegrityViolationException) {
      LOG.debug("Slug is already exist.", dataIntegrityViolationException);
      throw new AlreadyExistException("Slug is already exist.");
    }
    return savedEntity;
  }

  /**
   * update category entity.
   *
   * @param actions update actions
   * @param entity  CategoryEntity
   * @return updated category entity.
   */
  @Transactional
  private Category updateCategoryEntity(List<UpdateAction> actions, Category entity) {
    actions.parallelStream().forEach(action -> {
      updateService.handle(entity, action);
    });

    return categoryRepository.save(entity);
  }

  /**
   * Gets category by id.
   *
   * @param id the id
   * @return the category by id
   * @throws NotExistException if the can not find CategoryEntity by the id
   */
  private Category getById(String id) {
    LOG.debug("Enter getById, id is {}.", id);

    Category categoryEntity = categoryRepository.findOne(id);

    if (categoryEntity == null) {
      LOG.debug("Fail getById, can not find category by id: {}.", id);
      throw new NotExistException("can not find category by id:" + id);
    }

    LOG.debug("End getById, id is {}, get CategoryEntity: {}.",
        id, categoryEntity.toString());
    return categoryEntity;
  }

  /**
   * Gets parent category.
   *
   * @param parentId the parent id
   * @return the parent category
   */
  private Category getParentCategory(String parentId) {
    Category parent = categoryRepository.findOne(parentId);
    ParentCategoryValidator.validate(parentId, parent);
    return parent;
  }

  /**
   * set ancestors.
   *
   * @param parentId the parent id
   * @param parent   the parent category
   * @return list of ancestors
   */
  private List<String> setAncestors(String parentId, Category parent) {
    List<String> ancestors = Lists.newArrayList();
    if (parent.getAncestors() != null) {
      ancestors = Lists.newArrayList(parent.getAncestors());
    }
    ancestors.add(parentId);
    return ancestors;
  }
}
