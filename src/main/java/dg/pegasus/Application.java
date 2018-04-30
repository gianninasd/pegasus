package dg.pegasus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Base class to launch the Spring Boot application as a Tomcat server.
 * Also allows the configuration of web request interceptors.
 * @author gianninasd
 */
@SpringBootApplication
public class Application implements WebMvcConfigurer {

  @Autowired
  LogInterceptor logInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(logInterceptor);
  }
  
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}