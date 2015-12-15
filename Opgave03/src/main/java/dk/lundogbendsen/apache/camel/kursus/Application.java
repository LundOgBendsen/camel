package dk.lundogbendsen.apache.camel.kursus;

import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.activemq.camel.component.ActiveMQConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ActiveMQComponent activeMQComponent() {
        ActiveMQConfiguration activeMQConfiguration = new ActiveMQConfiguration();
        activeMQConfiguration.setBrokerURL("tcp://localhost:61613");
        activeMQConfiguration.setUserName("admin");
        activeMQConfiguration.setPassword("admin");
        return new ActiveMQComponent(activeMQConfiguration);
    }
}
