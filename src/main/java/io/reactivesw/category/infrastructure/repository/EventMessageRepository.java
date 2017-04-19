package io.reactivesw.category.infrastructure.repository;

import io.reactivesw.category.domain.model.EventMessage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Event Message Repository.
 */
public interface EventMessageRepository extends JpaRepository<EventMessage, String>,
    JpaSpecificationExecutor<EventMessage> {
}
