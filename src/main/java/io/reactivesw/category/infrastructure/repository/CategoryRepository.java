package io.reactivesw.category.infrastructure.repository;

import io.reactivesw.category.domain.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Category Repository to handle database access.
 */
public interface CategoryRepository extends JpaRepository<Category, String>,
    JpaSpecificationExecutor {
  /**
   * Query category ids by ancestor id list.
   *
   * @param ancestorId the ancestorId
   * @return the list
   */
  @Query(value = "select c from Category c where ?1 member of c.ancestors")
  List<Category> querySubCategoriesByAncestorId(String ancestorId);

  /**
   * Query category by parent list.
   *
   * @param parentId the parent id
   * @return the list
   */
  List<Category> queryCategoryByParent(String parentId);

  /**
   * Delete category by id.
   *
   * @param categoryIds the ids
   */
  @Modifying
  @Transactional
  @Query(value = "delete from Category c where c.id in ?1")
  void deleteCategoryById(List<String> categoryIds);

  /**
   * Find category by slug.
   *
   * @param slug the slug
   * @return the category entity
   */
  Category findCategoryBySlug(String slug);
}