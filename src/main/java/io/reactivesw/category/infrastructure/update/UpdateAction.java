package io.reactivesw.category.infrastructure.update;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import io.reactivesw.category.application.model.action.SetDescription;
import io.reactivesw.category.application.model.action.SetExternalId;
import io.reactivesw.category.application.model.action.SetMetaDescription;
import io.reactivesw.category.application.model.action.SetMetaKeywords;
import io.reactivesw.category.application.model.action.SetMetaTitle;
import io.reactivesw.category.application.model.action.SetName;
import io.reactivesw.category.application.model.action.SetOrderHint;
import io.reactivesw.category.application.model.action.SetParent;
import io.reactivesw.category.application.model.action.SetSlug;

/**
 * configurations for common update actions that will be used in more thant one service
 * and this action also extends other action configure in each service.
 * Created by umasuo on 16/11/21.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "action")
@JsonSubTypes( {
    @JsonSubTypes.Type(value = SetName.class, name = "setName"),
    @JsonSubTypes.Type(value = SetSlug.class, name = "setSlug"),
    @JsonSubTypes.Type(value = SetDescription.class, name = "setDescription"),
    @JsonSubTypes.Type(value = SetParent.class, name = "setParent"),
    @JsonSubTypes.Type(value = SetOrderHint.class, name = "setOrderHint"),
    @JsonSubTypes.Type(value = SetExternalId.class, name = "setExternalId"),
    @JsonSubTypes.Type(value = SetMetaTitle.class, name = "setMetaTitle"),
    @JsonSubTypes.Type(value = SetMetaDescription.class, name = "setMetaDescription"),
    @JsonSubTypes.Type(value = SetMetaKeywords.class, name = "setMetaKeywords")})
public interface UpdateAction {
  /**
   * get action name.
   *
   * @return String
   */
  String getActionName();
}
