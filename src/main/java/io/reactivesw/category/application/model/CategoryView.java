package io.reactivesw.category.application.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import io.reactivesw.model.LocalizedString;
import io.reactivesw.model.Reference;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * category model.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryView {

  /**
   * The unique ID of the category.
   */
  private String id;

  /**
   * The current version of the category.
   */
  private Integer version;

  /**
   * create time.
   */
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
  private ZonedDateTime createdAt;

  /**
   * last modified time.
   */
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
  private ZonedDateTime lastModifiedAt;

  /**
   * The Name.
   */
  @JsonUnwrapped
  private LocalizedString name;

  /**
   * human-readable identifiers usually used as deep-link URL to the related category.
   * Each slug is unique across a merchant,
   * but a category can have the same slug for different languages.
   */
  private String slug;

  /**
   * The Description.
   */
  private LocalizedString description;

  /**
   * Contains the parent path towards the root category.
   */
  private List<Reference> ancestors;

  /**
   * A category that is the parent of this category in the category tree.
   */
  private Reference parent;

  /**
   * An attribute as base for a custom category order in one level.
   */
  private String orderHint;

  /**
   * externalId.
   */
  private String externalId;

  /**
   * The Meta title.
   */
  private LocalizedString metaTitle;

  /**
   * The metaDescription.
   */
  private LocalizedString metaDescription;

  /**
   * the metaKeywords.
   */
  private LocalizedString metaKeywords;
}
