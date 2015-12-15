package dk.lundogbendsen.apache.camel.kursus;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainRoute extends RouteBuilder {
    @Autowired
    private UpperCaseBean upperCaseBean;

    @Override
    public void configure() throws Exception {
        from("file:input")
            .bean(upperCaseBean)
            .to("file:output");
    }
}
