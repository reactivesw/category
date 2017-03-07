package io.reactivesw.category;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by umasuo on 17/2/9.
 */
@SpringBootApplication(scanBasePackages = "io.reactivesw")
public class Application {

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
