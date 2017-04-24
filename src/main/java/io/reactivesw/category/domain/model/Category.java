package io.reactivesw.category.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * CategoryEntity Entity.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "category")
@EntityListeners(AuditingEntityListener.class)
public class Category {


  /**
   * The id.
   */
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "id")
  private String id;

  /**
   * The Created at.
   */
  @CreatedDate
  @Column(name = "created_at")
  private ZonedDateTime createdAt;

  /**
   * The Last modified at.
   */
  @LastModifiedDate
  @Column(name = "last_modified_at")
  private ZonedDateTime lastModifiedAt;

  /**
   * The version.
   */
  @Version
  @Column(name = "version")
  private Integer version;

  /**
   * The name.
   */
  @ElementCollection
  @CollectionTable(name = "category_name")
  private Set<LocalizedStringValue> name;


  /**
   * The slug.
   */
  @Column(length = 256, unique = true)
  private String slug;

  /**
   * The description.
   */
  @ElementCollection
  @CollectionTable(name = "category_description")
  private Set<LocalizedStringValue> description;

  /**
   * The ancestors.
   */
  @ElementCollection
  private List<String> ancestors;

  /**
   * The parent id.
   */
  @Column
  private String parent;

  /**
   * The order hint.
   */
  @Column(name = "order_hint")
  private String orderHint;

  /**
   * The external id.
   */
  @Column(name = "external_id")
  private String externalId;

  /**
   * The meta title.
   */
  @ElementCollection
  @CollectionTable(name = "category_meta_title")
  private Set<LocalizedStringValue> metaTitle;

  /**
   * The meta description.
   */
  @ElementCollection
  @CollectionTable(name = "category_meta_description")
  private Set<LocalizedStringValue> metaDescription;

  /**
   * The meta key works.
   */
  @ElementCollection
  @CollectionTable(name = "category_meta_keywords")
  private Set<LocalizedStringValue> metaKeyWords;

}