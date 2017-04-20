package io.reactivesw.category.application.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.reactivesw.model.LocalizedString;
import io.reactivesw.model.Reference;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Category draft is used to transfer data.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryDraft {

  /**
   * the name.
   */
  @NotNull
  private LocalizedString name;

  /**
   * the description.
   */
  private LocalizedString description;

  /**
   * Reference to a CategoryEntity.
   * A category that is the parent of this category in the category tree.
   */
  private Reference parent;

  /**
   * human-readable identifier usually used as deep-link URL to the related category.
   * Allowed are alphabetic, numeric, underscore (_) and hyphen (-) characters.
   * Maximum size is 256.
   * Must be unique across a merchant!
   * The same category can have the same slug for different languages.
   */
  @NotNull
  @Pattern(regexp = "[-a-zA-Z0-9_]{2,256}", message = "category slug can not match")
  private String slug;

  /**
   * An attribute as base for a custom category order in one level.
   */
  private String orderHint;

  /**
   * The External id.
   */
  private String externalId;

  /**
   * The Meta title.
   */
  private LocalizedString metaTitle;

  /**
   * The Meta description.
   */
  private LocalizedString metaDescription;

  /**
   * The Meta keywords.
   */
  private LocalizedString metaKeywords;
}
