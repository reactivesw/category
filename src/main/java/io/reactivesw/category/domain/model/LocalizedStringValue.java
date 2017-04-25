package io.reactivesw.category.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The value of localizedString.
 */
@Embeddable
@Data
@EqualsAndHashCode(callSuper = false)
public class LocalizedStringValue {

  /**
   * Id.
   */

  /**
   * Language.
   */
  private String language;

  /**
   * Text value.
   */
  private String text;

  /**
   * Build localized string value.
   *
   * @param language the language
   * @param text the text
   * @return the localized string value
   */
  public static LocalizedStringValue build(String language, String text) {
    LocalizedStringValue value = new LocalizedStringValue();

    value.setLanguage(language);
    value.setText(text);

    return value;
  }

}
