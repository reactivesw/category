package io.reactivesw.category.application.model.mapper;

import io.reactivesw.category.application.model.CategoryDraft;
import io.reactivesw.category.application.model.CategoryView;
import io.reactivesw.category.domain.model.Category;
import io.reactivesw.category.infrastructure.util.ReferenceTypes;
import io.reactivesw.model.Reference;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Davis on 16/11/28.
 */
public final class CategoryMapper {

  /**
   * constructor.
   */
  private CategoryMapper() {
  }

  /**
   * Map entity to category category.
   *
   * @param entity the entity
   * @return the category
   */
  public static CategoryView toModel(Category entity) {
    CategoryView category = new CategoryView();
    //add reference type.
    category.setId(entity.getId());
    category.setAncestors(convertToReferenceList(entity.getAncestors()));
    String parentId = entity.getParent();
    //add reference type to parent.
    if (StringUtils.isNotBlank(parentId)) {
      category.setParent(new Reference(ReferenceTypes.CATEGORY.getType(), parentId));
    }
    category.setName(LocalizedStringMapper.toModelDefaultNull(entity.getName()));
    category.setSlug(entity.getSlug());
    category.setDescription(LocalizedStringMapper.toModelDefaultNull(entity
        .getDescription()));
    category.setOrderHint(entity.getOrderHint());
    category.setExternalId(entity.getExternalId());
    category.setMetaTitle(LocalizedStringMapper.toModelDefaultNull(entity
        .getMetaTitle()));
    category.setMetaKeywords(LocalizedStringMapper.toModelDefaultNull(entity
        .getMetaKeyWords()));
    category.setMetaDescription(LocalizedStringMapper.toModelDefaultNull(entity
        .getMetaDescription()));
    category.setVersion(entity.getVersion());
    category.setCreatedAt(entity.getCreatedAt());
    category.setLastModifiedAt(entity.getLastModifiedAt());
    return category;
  }

  /**
   * convert List of CategoryEntity to List of Category.
   *
   * @param entities the List of CategoryEntity
   * @return the List of Category
   */
  public static List<CategoryView> toModel(List<Category> entities) {
    return entities.stream().map(
        entity -> toModel(entity)
    ).collect(Collectors.toList());
  }


  /**
   * mapper Draft to category entity.
   *
   * @param model the draft
   * @return the category entity
   */
  public static Category toEntity(CategoryDraft model) {
    Category entity = new Category();

    entity.setName(LocalizedStringMapper.toEntityDefaultNull(model.getName()));
    entity.setDescription(LocalizedStringMapper.toEntityDefaultNull(model.getDescription()));
    entity.setSlug(model.getSlug());
    entity.setOrderHint(model.getOrderHint());
    entity.setExternalId(model.getExternalId());
    entity.setMetaTitle(LocalizedStringMapper.toEntityDefaultNull(model.getMetaTitle()));
    entity.setMetaDescription(LocalizedStringMapper.toEntityDefaultNull(
        model.getMetaDescription()));
    entity.setMetaKeyWords(LocalizedStringMapper.toEntityDefaultNull(model.getMetaKeywords()));
    return entity;
  }

  /**
   * transfer List of ancestor ids to List of Reference about category.
   *
   * @param ancestors list of ancestor ids
   * @return list of Reference
   */
  private static List<Reference> convertToReferenceList(List<String> ancestors) {
    List<Reference> result = new ArrayList<>();
    if (ancestors != null) {
      String typeId = ReferenceTypes.CATEGORY.getType();
      result = ancestors.stream()
          .map(ancestor -> new Reference(typeId, ancestor))
          .collect(Collectors.toList());
    }
    return result;
  }
}
