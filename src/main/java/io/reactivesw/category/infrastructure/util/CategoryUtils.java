package io.reactivesw.category.infrastructure.util;

import com.google.common.collect.Lists;

import io.reactivesw.category.domain.model.Category;
import io.reactivesw.category.domain.model.LocalizedStringValue;

import java.util.List;

/**
 * Utils for category.
 */
public final class CategoryUtils {
  /**
   * Instantiates a new CategoryView update.
   */
  private CategoryUtils() {
  }

  /**
   * get all names from list of CategoryEntity.
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

  /**
   * Get order hint by current system time.
   * @return order hint
   */
  public static String getOrderHint() {
    long currentTime = System.currentTimeMillis();
    int length = String.valueOf(currentTime).length();
    double divisor = Math.pow(10, length);
    //    convert current time to decimal
    return String.valueOf(currentTime / divisor);
  }
}
