package io.reactivesw.category.infrastructure.util;

import org.springframework.data.domain.AuditorAware;

/**
 * Auditor aware for zone date.
 */
public class ZonedDateTimeAuditorAware implements AuditorAware<String> {

  /**
   * Get current auditor.
   * @return null
   */
  @Override
  public String getCurrentAuditor() {
    return null;
  }
}