package application;

import java.util.Arrays;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.Payload;

@SpringBootApplication
@Slf4j
@RabbitListener(queues = "post")
public class Application {


  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
    return new Jackson2JsonMessageConverter();
  }

  @RabbitHandler
  public void process(@Payload String foo) {
    log.info(new Date() + ": " + foo);
  }

  @Bean
  public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
    return args -> {
      log.info("Let's inspect the beans provided by Spring Boot:");

    };
  }
}
