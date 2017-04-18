package io.reactivesw.category.infrastructure;

/**
 * Url for category service.
 */
public class Router {

  /**
   * The constant CATEGORY_ROOT.
   */
  public static final String CATEGORY_ROOT = "/";

  /**
   * categoryId.
   */
  public static final String CATEGORY_ID = "categoryId";

  /**
   * The constant CATEGORY_SLUG.
   */
  public static final String CATEGORY_SLUG = "categorySlug";

  /**
   * category url with id.
   */
  public static final String CATEGORY_WITH_ID = CATEGORY_ROOT + "/{" + CATEGORY_ID + "}";

  /**
   * The constant CATEGORY_WITH_SLUG.
   */
  public static final String CATEGORY_WITH_SLUG = CATEGORY_ROOT + "{" + CATEGORY_SLUG + "}";

  /**
   * The constant CATEGORY_HELTH_CHECK.
   */
  public static final String CATEGORY_HELTH_CHECK = CATEGORY_ROOT + "health";
}
