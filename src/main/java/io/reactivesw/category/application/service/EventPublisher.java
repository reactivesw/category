package io.reactivesw.category.application.service;

import io.reactivesw.category.domain.model.EventMessage;
import io.reactivesw.category.domain.service.EventMessageService;
import io.reactivesw.category.infrastructure.configuration.EventConfig;
import io.reactivesw.category.infrastructure.util.EventTopics;
import io.reactivesw.message.client.core.DefaultProducerFactory;
import io.reactivesw.message.client.core.Message;
import io.reactivesw.message.client.producer.Producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * EventMessage Publisher.
 */
@Service
public class EventPublisher {

  /**
   * logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(EventPublisher.class);


  /**
   * Producer map.
   */
  private transient final Map<String, Producer> producerMap = new ConcurrentHashMap<>();

  /**
   * The Message service.
   */
  @Autowired
  public transient EventMessageService messageService;

  /**
   * Instantiates a new Event publisher.
   */
  public EventPublisher(EventConfig config) {
    Producer deletedProducer = DefaultProducerFactory.createGoogleProducer(
        config.getGoogleCloudProjectId(), EventTopics.DELETED_CATEGORY);

    producerMap.put(EventTopics.DELETED_CATEGORY, deletedProducer);
  }

  /**
   * Executor.
   * Executes each 200 ms.
   */
  @Scheduled(fixedRate = 200)
  public void executor() {

    List<EventMessage> events = messageService.getEvents();

    events.stream().forEach(
        event -> {
          Message message = Message.build(event.getId(), null,
              event.getCreatedTime(), event.getVersion(), event.getExpire(), event.getData());

          publishEvent(event.getTopic(), message);
        }
    );

    messageService.deleteEvents(events);
  }

  /**
   * Publish an event to a topic.
   *
   * @param topicName topic name
   * @param message event
   */
  private void publishEvent(String topicName, Message message) {

    Producer producer = producerMap.get(topicName);
    if (producer == null) {
      // The topic may be deleted or changed name.
      LOG.error("Producer not found. topicName: {}.", topicName);
    } else {
      LOG.debug("Publish event. topicName: {}, event: {}.", topicName, message);
      producer.publish(message);
    }
  }
}
