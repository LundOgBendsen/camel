package dk.lundogbendsen.apache.camel.kursus;

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
                .to("direct:getOrders");

        from("direct:getOrders")
            .process(
                exchange -> {
                    exchange.getIn().setBody(orderRepository.findAll());
                }
            );
    }
}
