package com.dzone.sboot.camel.routes;

import com.dzone.sboot.camel.processors.CSVToJSONProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CSVToJsonProcessor extends RouteBuilder {

  @Autowired private CSVToJSONProcessor csvToJSONProcessor;

  @Override
  public void configure() throws Exception {
    from("direct:csvToJsonRoute").process(csvToJSONProcessor);
  }
}
