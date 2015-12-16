package dk.lundogbendsen.apache.camel.kursus;

import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainRoute extends RouteBuilder {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void configure() throws Exception {
        restConfiguration()
            .component("restlet")
            .bindingMode(RestBindingMode.json);

        rest("/rest")
            .get("/order")
                .to("direct:getOrders")
            .get("/order/{id}")
                .to("direct:getOrder");

        from("direct:getOrders")
            .process(
                exchange -> {
                    exchange.getIn().setBody(orderRepository.findAll());
                }
            );

        from("direct:getOrder")
            .process(
                exchange -> {
                    Message in = exchange.getIn();
                    in.setBody(orderRepository.findOne(in.getHeader("id", Long.class)));
                }
            );
    }
}
