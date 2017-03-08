package io.reactivesw.category.infrastructure.util;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

/**
 * Created by Davis on 17/3/7.
 */
public class ZonedDateTimeAuditorAware implements AuditorAware<String> {

  @Override
  public String getCurrentAuditor() {
    return null;
  }
}