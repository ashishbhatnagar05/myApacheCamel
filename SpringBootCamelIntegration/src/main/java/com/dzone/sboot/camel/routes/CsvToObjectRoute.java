package com.dzone.sboot.camel.routes;

import com.dzone.sboot.camel.processors.CSVToObjectProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CsvToObjectRoute extends RouteBuilder {
    @Autowired
    CSVToObjectProcessor csvToObjectProcessor;

    @Override
    public void configure() throws Exception {
        from("direct:csvToObject").process(csvToObjectProcessor);
    }
}
