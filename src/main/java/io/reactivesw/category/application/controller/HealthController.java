package io.reactivesw.category.application.controller;

import io.reactivesw.category.infrastructure.Router;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by umasuo on 17/2/21.
 */
@RestController
public class HealthController {

  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(HealthController.class);

  /**
   * service name.
   */
  @Value("${spring.application.name}")
  private transient String serviceName;

  /**
   * this api is used for health check.
   *
   * @return service name.
   */
  @GetMapping(Router.CATEGORY_HELTH_CHECK)
  public String healthCheck() {
    LOG.debug("Enter healthCheck.");

    return serviceName + ", system time: " + System.currentTimeMillis();
  }
}
