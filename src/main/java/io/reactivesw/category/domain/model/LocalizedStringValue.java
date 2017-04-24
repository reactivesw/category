package io.reactivesw.category.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Embeddable;

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
//  @Id
//  @GeneratedValue(generator = "uuid")
//  @GenericGenerator(name = "uuid", strategy = "uuid2")
//  @Column(name = "id")
//  private String id;

  /**
   * Language.
   */
//  @Column
  private String language;

  /**
   * Text value.
   */
//  @Column(columnDefinition = "text")
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
