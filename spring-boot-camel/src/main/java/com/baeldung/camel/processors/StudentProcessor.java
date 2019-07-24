package com.baeldung.camel.processors;

import com.baeldung.camel.model.Student;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class StudentProcessor implements Processor {

  public void process(Exchange exchange) throws Exception {
    Student bodyIn = (Student) exchange.getIn().getBody();
    bodyIn.setName("Hello, " + bodyIn.getName());
    bodyIn.setId(bodyIn.getId() * 10);
    exchange.getIn().setBody(bodyIn);
  }

}
