package io.reactivesw.category.application.model.mapper;

import io.reactivesw.category.domain.model.EventMessage;
import io.reactivesw.category.infrastructure.enums.EventStatus;
import io.reactivesw.category.infrastructure.util.EventTopics;

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
  public static EventMessage build() {
    EventMessage message = new EventMessage();

    // TODO: 17/4/17 change version code to config
    message.setVersion(1);
    message.setCreatedTime(System.currentTimeMillis());
    message.setStatus(EventStatus.CREATED);
    message.setTopic(EventTopics.DELETED_CATEGORY);

    return message;
  }
}
