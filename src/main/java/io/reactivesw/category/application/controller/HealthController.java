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
    LOG.info("Enter. Health check.");

    Long currentTime = System.currentTimeMillis();

    LOG.info("Exit. Health checked, service name is {}, current time is {}.", serviceName,
        currentTime);
    return serviceName + ", system time: " + currentTime;

  }
}
