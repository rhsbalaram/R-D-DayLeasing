package com.dayLeasing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.JndiConnectionFactoryAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.dayLeasing.configuration.Configuration;


// TODO: Auto-generated Javadoc
/**
 * The Class DayLeasingApplication.
 * Application startup using spring boot.
 */
/**
 * @author Balaram
 *
 */
@Import({ Configuration.class })
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class,
    DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class,
    JndiConnectionFactoryAutoConfiguration.class, JpaRepositoriesAutoConfiguration.class })
@SpringBootApplication(scanBasePackages = { "com.dayLeasing" })
// same as @Configuration @EnableAutoConfiguration @ComponentScan combined
public class DayLeasingApplication {

  /**
   * The main method.
   *
   * @param args
   *          the arguments
   */
  public static void main(String[] args) {
    SpringApplication.run(DayLeasingApplication.class, args);
  }

  /**
   * Mvc configurer to allow CROS origins.
   *
   * @return the web mvc configurer
   */
  @Bean
  public WebMvcConfigurer mvcConfigurer() {
    return new WebMvcConfigurerAdapter() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*")
            .allowedMethods("GET", "PUT", "POST", "OPTIONS", "DELETE");
      }
    };
  }

}
