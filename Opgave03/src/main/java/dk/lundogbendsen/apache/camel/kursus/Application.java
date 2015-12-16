package dk.lundogbendsen.apache.camel.kursus;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ActiveMQConnectionFactory jmsFactory() {
        return new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");
    }
}
