package com.camel.example.camelexample;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class CamelExampleApplication extends RouteBuilder {

  public static void main(String[] args) {
    SpringApplication.run(CamelExampleApplication.class, args);
  }

  @Override
  public void configure() throws Exception {
    System.out.println("started");
    // copyAllFilesFromSourceToDestination();
    // copySpecificFileFromSourceToDestination(".json");
    // doSimpleProcessOnFile();
    // convertCsvToPOJO();
    // processStudentList();
    defineRest();
    // defineRestRoute();
    // TODO: work in progress
    defineCsvRest();
    processStudentList();
    System.out.println("Ends");
  }

  private void defineRest() {
    restConfiguration().host("localhost").port(1002);
  }

  private void defineCsvRest() {

    rest("/rest/v1").post("/student").to("direct:unmarshal");

    from("direct:unmarshal")
        .unmarshal(new BindyCsvDataFormat(Student.class))
        .to("direct:processStudentList");
    ;
    //    from("direct:restCsv")
    //        .process(
    //            p -> {
    //              InputStream stream = (InputStream) p.getIn().getBody();
    //              String input = IOUtils.toString(stream, Charsets.UTF_8);
    //            });
  }

  private void defineRestRoute() {
    rest("/rest/v1").post("/input").to("file:destination/input.json");
  }

  private void convertCsvToPOJO() {
    from("file:source?noop=true&fileName=student.csv")
        .unmarshal(new BindyCsvDataFormat(Student.class))
        .to("direct:processStudentList");
  }

  private void processStudentList() {
    from("direct:processStudentList")
        .process(
            p -> {
              if (p.getIn().getBody() instanceof List) {
                List<Student> list = (List<Student>) p.getIn().getBody();
                if (list.size() != 0) {
                  list.stream()
                      .forEach(
                          s -> {
                            System.out.println("Student Id: " + s.getId());
                            System.out.println("Student firstName: " + s.getFirstName());
                            System.out.println("Student lastName: " + s.getLastName());
                          });
                }
              }
            });
  }

  private void copyAllFilesFromSourceToDestination() {
    from("file:source?noop=true").to("file:destination");
  }

  private void copySpecificFileFromSourceToDestination(String fileType) {
    from("file:source?noop=true")
        .filter(header(Exchange.FILE_NAME).endsWith(fileType))
        .to("file:destination");
  }
}
