package io.reactivesw.category.application.controller;

import com.google.common.collect.Maps;

import io.reactivesw.category.infrastructure.Router;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import sun.net.www.http.HttpClient;

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
    LOG.debug("enter healthCheck");

    return serviceName + ", system time: " + System.currentTimeMillis();
  }
}
