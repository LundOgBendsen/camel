package dk.lundogbendsen.apache.camel.kursus;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MainRoute extends RouteBuilder
{
    @Override
    public void configure() throws Exception
    {
        from("file:input")
            .to("file:output");
    }
}
