package io.reactivesw.category;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by umasuo on 17/2/9.
 */
@EnableSwagger2
@SpringBootApplication(scanBasePackages = "io.reactivesw")
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
