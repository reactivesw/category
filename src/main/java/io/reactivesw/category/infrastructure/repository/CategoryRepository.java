package io.reactivesw.category.infrastructure.repository;

import io.reactivesw.category.domain.model.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

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
   * Find order by order hint list.
   *
   * @return the list
   */
  @Query("select c from Category c order by c.orderHint asc")
  List<Category> findOrderByOrderHint();
}