package io.reactivesw.category.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The value of localizedString.
 */
@Entity
@Table(name = "common_localized_String")
@Data
@EqualsAndHashCode(callSuper = false)
public class LocalizedStringValue {

  /**
   * Id.
   */
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "id")
  private String id;

  /**
   * language.
   */
  @Column
  private String language;

  /**
   * text value.
   */
  @Column(columnDefinition = "text")
  private String text;

  /**
   * Build localized string value.
   *
   * @param language the language
   * @param text     the text
   * @return the localized string value
   */
  public static LocalizedStringValue build(String language, String text) {
    LocalizedStringValue value = new LocalizedStringValue();

    value.setLanguage(language);
    value.setText(text);

    return value;
  }

}
