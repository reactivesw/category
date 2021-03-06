package io.reactivesw.category.infrastructure.repository;


import io.reactivesw.category.domain.model.EventMessage;
import io.reactivesw.category.infrastructure.enums.EventStatus;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * The type Event message specification.
 */
public final class EventMessageSpecification {

  /**
   * Instantiates a new Event message specification.
   */
  private EventMessageSpecification() {
  }

  /**
   * Specification for fetch Events.
   *
   * @return Specification
   */
  public static Specification<EventMessage> isAvailable() {
    return new Specification<EventMessage>() {
      /**
       * Predicate builder.
       */
      public Predicate toPredicate(Root<EventMessage> root, CriteriaQuery<?> query,
          CriteriaBuilder builder) {
        // Fetch events for two kind of conditions.
        return builder.or(
            builder.and(
                // Condition1: Fetch event whose status is "PENDING", but created 1 minutes ago.
                builder.lessThan(root.get("createdTime"), System.currentTimeMillis() - 60000),
                builder.equal(root.get("status"), EventStatus.PENDING.getValue())
            ),
            // Condition2: Fetch event whose status is "CREATED"
            builder.equal(root.get("status"), EventStatus.CREATED.getValue())
        );
      }
    };
  }
}
