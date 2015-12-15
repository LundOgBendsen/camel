package dk.lundogbendsen.apache.camel.kursus;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.xml.Namespaces;
import org.springframework.stereotype.Component;

@Component
public class MainRoute extends RouteBuilder {
    private final static Namespaces NAMESPACES = new Namespaces("prefix", "http://www.lundogbendsen.dk/apache/camel/kursus");

    @Override
    public void configure() throws Exception {
        from("file:input")
            .split(xpath("/prefix:orders/prefix:order").namespaces(NAMESPACES))
            .choice()
                .when(xpath("prefix:order/prefix:orderType='Software'").namespaces(NAMESPACES)).to("jms:queue:softwareOrders")
                .when(xpath("prefix:order/prefix:orderType='Hardware'").namespaces(NAMESPACES)).to("jms:queue:hardwareOrders")
                .otherwise().to("jms:queue:unknownOrders")
            .endChoice();
    }
}
