package io.reactivesw.category.application.controller;

import io.reactivesw.category.infrastructure.Router;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller to check health status of service.
 */
@RestController
public class HealthController {

  /**
   * Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(HealthController.class);

  /**
   * Service name.
   */
  @Value("${spring.application.name}")
  private transient String serviceName;

  /**
   * This api is used to check health.
   *
   * @return service name.
   */
  @GetMapping(Router.CATEGORY_HELTH_CHECK)
  public String healthCheck() {
    LOG.debug("Enter healthCheck.");

    return serviceName + ", system time: " + System.currentTimeMillis();
  }
}
