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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * CategoryEntity Entity.
 * Created by Davis on 16/11/13.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "category")
@EntityListeners(AuditingEntityListener.class)
public class Category {

  /**
   * Id
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
   * version.
   */
  @Version
  @Column(name = "version")
  private Integer version;

  /**
   * The Name.
   */
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private Set<LocalizedStringValue> name;

  /**
   * slug.
   */
  @Column(length = 256, unique = true)
  private String slug;

  /**
   * The Description.
   */
  @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
  private Set<LocalizedStringValue> description;

  /**
   * ancestors.
   */
  @ElementCollection
  private List<String> ancestors;

  /**
   * parent id.
   */
  @Column
  private String parent;

  /**
   * order hint.
   */
  @Column(name = "order_hint")
  private String orderHint;

  /**
   * external id.
   */
  @Column(name = "external_id")
  private String externalId;

  /**
   * meta title.
   */
  @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
  private Set<LocalizedStringValue> metaTitle;

  /**
   * meta description.
   */
  @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
  private Set<LocalizedStringValue> metaDescription;

  /**
   * meta key works.
   */
  @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
  private Set<LocalizedStringValue> metaKeyWords;

}