package io.reactivesw.category.domain.service;

import io.reactivesw.category.application.model.mapper.EventMessageMapper;
import io.reactivesw.category.domain.model.EventMessage;
import io.reactivesw.category.infrastructure.enums.EventStatus;
import io.reactivesw.category.infrastructure.repository.EventMessageRepository;
import io.reactivesw.category.infrastructure.repository.EventMessageSpecification;
import io.reactivesw.message.client.utils.serializer.JsonSerializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * Event Message Service.
 */
@Service
public class EventMessageService {
  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(EventMessageService.class);

  /**
   * Event message repository.
   */
  private transient EventMessageRepository eventMessageRepository;


  /**
   * json serializer.
   */
  private transient static JsonSerializer jsonSerializer = new JsonSerializer();

  /**
   * Instantiates a new Event message service.
   *
   * @param eventMessageRepository the event message repository
   */
  @Autowired
  public EventMessageService(EventMessageRepository eventMessageRepository) {
    this.eventMessageRepository = eventMessageRepository;
  }

  /**
   * Save deleted event.
   *
   * @param categoryIds the category ids
   */
  public void saveDeletedEvent(List<String> categoryIds) {
    LOG.debug("Enter. CategoryId: {}", categoryIds);
    EventMessage message = EventMessageMapper.build();
    message.setData(jsonSerializer.serialize(categoryIds));

    EventMessage savedMessage = eventMessageRepository.save(message);

    LOG.debug("End. EventId: {}.", savedMessage.getId());
  }


  /**
   * Gets events.
   *
   * @return the events
   */
  @Transactional
  public List<EventMessage> getEvents() {
    LOG.debug("Enter.");
    //fetch the first page and fetch 10 event each time. TODO change this to configuration.
    Page<EventMessage> page = eventMessageRepository.findAll(
        EventMessageSpecification.isAvailable(), new PageRequest(0, 10));
    List<EventMessage> events = page.getContent();

    LOG.debug("Fetch events. eventCount: {}, countInDb: {}.", events.size(), page
        .getTotalElements());

    events.stream().forEach(
        event -> {
          if (event.getStatus() == EventStatus.CREATED) {
            //if the event is in "CREATED" status, then change it to "PENDING"
            event.setStatus(EventStatus.PENDING);
          }
        }
    );

    eventMessageRepository.save(events);

    // Log the event data.
    LOG.trace("Fetch events. Events: {}.", Arrays.toString(events.toArray()));
    LOG.debug("Exit.");
    return events;
  }
}