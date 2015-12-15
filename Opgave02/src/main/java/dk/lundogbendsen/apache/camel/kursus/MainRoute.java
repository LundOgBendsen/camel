package dk.lundogbendsen.apache.camel.kursus;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.processor.idempotent.MemoryIdempotentRepository;
import org.springframework.stereotype.Component;

@Component
public class MainRoute extends RouteBuilder
{
    @Override
    public void configure() throws Exception
    {
        from("file:input")
            .idempotentConsumer(
                header(Exchange.FILE_NAME),
                MemoryIdempotentRepository.memoryIdempotentRepository()
            )
            .to("file:output");
    }
}
