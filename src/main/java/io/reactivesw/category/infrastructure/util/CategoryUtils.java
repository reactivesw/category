package io.reactivesw.category.infrastructure.util;

import com.google.common.collect.Lists;

import io.reactivesw.category.domain.model.Category;
import io.reactivesw.category.domain.model.LocalizedStringValue;

import java.util.List;

/**
 * Created by Davis on 16/12/28.
 */
public final class CategoryUtils {
  /**
   * Instantiates a new CategoryView update.
   */
  private CategoryUtils() {
  }

  /**
   * get all names from list of CategoryEntity.
   *
   * @param categories list of CategoryEntity
   * @return list of LocalizedStringEntity
   */
  public static List<LocalizedStringValue> getAllCategoryNames(List<Category> categories) {
    List<LocalizedStringValue> categoryNames = Lists.newArrayList();
    if (categories != null) {
      categories.stream()
          .map(categoryEntity -> categoryEntity.getName())
          .forEach(categoryNames::addAll);
    }
    return categoryNames;
  }
}
