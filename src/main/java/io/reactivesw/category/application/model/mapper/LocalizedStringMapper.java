package io.reactivesw.category.application.model.mapper;

import io.reactivesw.category.domain.model.LocalizedStringValue;
import io.reactivesw.model.LocalizedString;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Mapper for localizedString.
 */
public final class LocalizedStringMapper {

  /**
   * Instantiates a new Localized string mapper.
   */
  private LocalizedStringMapper() {
  }

  /**
   * Convert to localized string.
   * when localizedStringEntity is null return new LocalizedString.
   *
   * @param localizedStringEntities the localized string entity
   * @return the localized string
   */
  public static LocalizedString toModelDefaultNew(Set<LocalizedStringValue>
                                                      localizedStringEntities) {
    LocalizedString localizedString = new LocalizedString();
    if (localizedStringEntities != null) {
      localizedString = toModel(localizedStringEntities);
    }
    return localizedString;
  }

  /**
   * Convert to localized string.
   * when localizedStringEntity is null return null.
   *
   * @param localizedStringEntities the localized string entity
   * @return the localized string
   */
  public static LocalizedString toModelDefaultNull(Set<LocalizedStringValue>
                                                       localizedStringEntities) {
    LocalizedString localizedString = null;
    if (localizedStringEntities != null) {
      localizedString = toModel(localizedStringEntities);
    }
    return localizedString;
  }


  /**
   * Convert to Set build LocalizedStringEntity from LocalizedString.
   *
   * @param localizedString the LocalizedString
   * @return Set build LocalizedStringEntity
   */
  public static Set<LocalizedStringValue> toEntityDefaultNew(LocalizedString
                                                                 localizedString) {
    Set<LocalizedStringValue> localizedStringEntities = new HashSet<>();

    if (localizedString != null && !localizedString.getLocalized().isEmpty()) {
      localizedStringEntities = toEntity(localizedString, localizedStringEntities);
    }

    return localizedStringEntities;
  }


  /**
   * Model to entity default null set.
   *
   * @param localizedString the localized string
   * @return the set
   */
  public static Set<LocalizedStringValue> toEntityDefaultNull(LocalizedString
                                                                  localizedString) {
    Set<LocalizedStringValue> localizedStringEntities = null;

    if (localizedString != null && !localizedString.getLocalized().isEmpty()) {
      localizedStringEntities = new HashSet<>();
      toEntity(localizedString, localizedStringEntities);
    }

    return localizedStringEntities;
  }

  /**
   * Convert to localized string.
   *
   * @param localizedStringEntities localizedStringEntities
   * @return LocalizedString
   */
  private static LocalizedString toModel(Set<LocalizedStringValue>
                                             localizedStringEntities) {
    LocalizedString localizedString = new LocalizedString();
    for (LocalizedStringValue localizedStringEntity : localizedStringEntities) {
      localizedString.addKeyValue(localizedStringEntity.getLanguage(), localizedStringEntity
          .getText());
    }
    return localizedString;
  }

  /**
   * build LocalizedStringValue from Map.Entry.
   *
   * @param localizedValue Map.Entry
   * @return LocalizedStringValue
   */
  private static LocalizedStringValue build(Map.Entry<String, String> localizedValue) {
    return LocalizedStringValue.build(localizedValue.getKey().toString(),
        localizedValue.getValue().toString());
  }

  /**
   * convert LocalizedString  to Set of LocalizedStringValue.
   *
   * @param localizedString         LocalizedString
   * @param localizedStringEntities Set of LocalizedStringValue
   * @return Set of LocalizedStringValue
   */
  private static Set<LocalizedStringValue> toEntity(LocalizedString localizedString,
                                                    Set<LocalizedStringValue>
                                                        localizedStringEntities) {
    Set<Map.Entry<String, String>> localizedStrings = localizedString.getLocalized().entrySet();
    for (Map.Entry localizedValue : localizedStrings) {
      localizedStringEntities.add(build(localizedValue));
    }

    return localizedStringEntities;
  }
}
