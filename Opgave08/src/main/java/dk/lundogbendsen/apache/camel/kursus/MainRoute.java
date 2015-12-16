package dk.lundogbendsen.apache.camel.kursus;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MainRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        restConfiguration().component("restlet");

        rest("/rest")
            .get("/order").to("direct:getOrder");

        from("direct:getOrder")
            .transform().constant("Orders");
    }
}
