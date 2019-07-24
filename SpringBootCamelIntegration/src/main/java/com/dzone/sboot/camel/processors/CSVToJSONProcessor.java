package com.dzone.sboot.camel.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class CSVToJSONProcessor implements Processor {
  @Override
  public void process(Exchange exchange) throws Exception {
    String csv = (String) exchange.getIn().getBody();
    String[] lines = csv.split("/n");
    for (String line : lines) {
      String[] parameters = line.split(",");
      for (int i = 0; i < parameters.length; i++) {
        if (i == 0) {
          System.out.println("ID: " + parameters[i]);
        } else {
          System.out.println("NAME: " + parameters[i]);
        }
      }
    }
  }
}
