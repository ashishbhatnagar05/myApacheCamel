package com.dzone.sboot.camel.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;


@Component
public class CSVToObjectProcessor implements Processor {

        public void process(List<List> csvRows) {
            for (List csvRow : csvRows) {
                Person person = new Person();
                person.setFirstName(csvRow.get(0));
                person.setLastName(csvRow.get(1));
                doSomethingTo(person);
            }
        }
    }
}
