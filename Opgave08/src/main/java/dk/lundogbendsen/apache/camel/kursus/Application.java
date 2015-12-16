package dk.lundogbendsen.apache.camel.kursus;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.component.restlet.RestletComponent;
import org.restlet.Component;
import org.restlet.ext.spring.SpringServerServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ServletRegistrationBean dispatcherRegistration() {
        final SpringServerServlet springServerServlet = new SpringServerServlet();

        final ServletRegistrationBean registration = new ServletRegistrationBean(springServerServlet, "/rest/*");

        final HashMap<String, String> initParameters = new HashMap<>();
        initParameters.put("org.restlet.component", "restletComponent");
        registration.setInitParameters(initParameters);

        return registration;
    }

    @Bean
    public Component restletComponent() {
        return new Component();
    }

    @Bean
    public RestletComponent restletComponentService(final Component component) {
        return new RestletComponent(component);
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public JdbcTemplate jdbcTemplate(final DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public ActiveMQConnectionFactory jmsFactory() {
        return new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");
    }
}
