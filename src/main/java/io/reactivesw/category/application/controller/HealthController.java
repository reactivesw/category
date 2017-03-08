package io.reactivesw.category.application.controller;

import io.reactivesw.category.infrastructure.Router;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import sun.net.www.http.HttpClient;

/**
 * Created by umasuo on 17/2/21.
 */
@RestController
public class HealthController {

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
    RestTemplate restTemplate = new RestTemplate();
    Object object = restTemplate.getForObject("https://api.github.com", Object.class);
    return serviceName + ", system time: " + System.currentTimeMillis() + " - " + object.toString();
  }
}
