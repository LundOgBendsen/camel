package dk.lundogbendsen.apache.camel.kursus;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MainRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("file:input")
            .split(xpath("/prefix:orders/prefix:order")
                    .namespace("prefix", "http://www.lundogbendsen.dk/apache/camel/kursus"))
            .to("jms:queue:orders");
    }
}
