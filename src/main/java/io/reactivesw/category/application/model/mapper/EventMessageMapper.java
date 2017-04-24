package io.reactivesw.category.application.model.mapper;

import io.reactivesw.category.domain.model.EventMessage;
import io.reactivesw.category.infrastructure.configuration.EventConfig;
import io.reactivesw.category.infrastructure.enums.EventStatus;

/**
 * Event Message Mapper.
 */
public final class EventMessageMapper {
  /**
   * Instantiates a new Event message mapper.
   */
  private EventMessageMapper() {
  }

  /**
   * Build event message.
   *
   * @return the event message
   */
  public static EventMessage build(EventConfig config) {
    EventMessage message = new EventMessage();

    message.setVersion(config.getDeleteCategoryVersion());
    message.setCreatedTime(System.currentTimeMillis());
    message.setStatus(EventStatus.CREATED);
    message.setTopic(config.getDeleteCategoryName());

    return message;
  }
}
