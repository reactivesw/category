package io.reactivesw.category.infrastructure;

/**
 * Created by umasuo on 16/12/20.
 */
public class Router {

  /**
   * The constant CATEGORY_ROOT.
   */
  public static final String CATEGORY_ROOT = "/categories";

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
}
