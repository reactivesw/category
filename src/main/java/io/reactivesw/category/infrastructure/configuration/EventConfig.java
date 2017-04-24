package io.reactivesw.category.infrastructure.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Event config.
 */
@Configuration
@Data
public class EventConfig {

  /**
   * Google cloud project id.
   */
  @Value("${io.reactivesw.message.google.project.id}")
  private String googleCloudProjectId;

  /**
   * Delete category topic name.
   */
  @Value("${io.reactivesw.message.topic.deletecategory.name}")
  private String deleteCategoryName;

  /**
   * Delete category topic version.
   */
  @Value("${io.reactivesw.message.topic.deletecategory.version}")
  private Integer deleteCategoryVersion;

}