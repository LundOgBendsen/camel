package dk.lundogbendsen.apache.camel.kursus;

import org.apache.camel.Body;
import org.springframework.stereotype.Component;

@Component
public class UpperCaseBean {
    public String toUpperCase(@Body  final String input) {
        return input.toUpperCase();
    }
}
