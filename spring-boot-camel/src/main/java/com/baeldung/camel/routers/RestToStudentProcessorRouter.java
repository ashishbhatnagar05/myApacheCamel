package com.baeldung.camel.routers;

import com.baeldung.camel.model.Student;
import com.baeldung.camel.processors.StudentProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.MediaType;

@Component
public class RestToStudentProcessorRouter extends RouteBuilder {

  @Value("${application.api.path}")
  String contextPath;

  @Autowired private StudentProcessor myProcessor;

  @Override
  public void configure() {
    defineRestService();
    from("direct:remoteService")
        .process(myProcessor)
        .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(201));
  }

  private void defineRestService() {
    rest("/api")
            .post("/student")
            .produces(MediaType.APPLICATION_JSON)
            .consumes(MediaType.APPLICATION_JSON)
            .bindingMode(RestBindingMode.auto)
            .type(Student.class)
            .enableCORS(true)
            .to("direct:remoteService");
  }

//  private void defineRestServiceExtra() {
//    rest("/api/")
//        .description("Test REST Service")
//        .id("api-route")
//        .post("/bean")
//        .produces(MediaType.APPLICATION_JSON)
//        .consumes(MediaType.APPLICATION_JSON)
//        .bindingMode(RestBindingMode.auto)
//        .type(Student.class)
//        .enableCORS(true)
//        .to("direct:remoteService");
//  }

  //  private void defineProcessor() {
  //    from("direct:remoteService")
  //            //        .routeId("direct-route")
  //            //        .tracing()
  //            //        .log(">>> ${body.id}")
  //            //        .log(">>> ${body.name}")
  //            .process(myProcessor)
  //            .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(201));
  //  }
  //  private void configureRest() {
  //    restConfiguration()
  //        .contextPath(contextPath) //
  //        .port(serverPort)
  //        .enableCORS(true)
  //        //        .apiContextPath("/api-doc")
  //        //        .apiProperty("api.title", "Test REST API")
  //        //        .apiProperty("api.version", "v1")
  //        //        .apiProperty("cors", "true") // cross-site
  //        //        .apiContextRouteId("doc-api")
  //        //
  //        .component("servlet")
  //        .bindingMode(RestBindingMode.json)
  //        .dataFormatProperty("prettyPrint", "true");
  //  }
}
