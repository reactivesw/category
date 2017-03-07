package io.reactivesw.category.application.model;

import lombok.Data;

import java.util.List;

/**
 * Created by Davis on 16/12/10.
 *
 * @param <T> the type parameter
 */
@Data
public class PagedQueryResult<T> {
  /**
   * The Offset.
   */
  private Integer offset;

  /**
   * The Count.
   */
  private Integer count;

  /**
   * The Total.
   */
  private Integer total;

  /**
   * The Results.
   */
  private List<T> results;

  /**
   * The Facets.
   */
  private Object facets;
}
