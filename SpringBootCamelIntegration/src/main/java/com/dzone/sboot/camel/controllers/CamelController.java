package com.dzone.sboot.camel.controllers;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/camel")
public class CamelController {

  @Autowired ProducerTemplate producerTemplate;

  @GetMapping("/start")
  public void startCamel() {
    producerTemplate.sendBody("direct:firstRoute", "Calling via Spring Boot Rest Controller");
  }

  @PostMapping("/CSVToJson")
  public void CSVToJson(@RequestBody String request) {
    producerTemplate.sendBody("direct:csvToJsonRoute", request);
  }
}