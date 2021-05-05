package ru.butorin;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class MainClient {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        Parameters parameters = context.getBean("parameters", Parameters.class);
        Client client = context.getBean("client",Client.class);
        client.communicationSession(parameters);

    }

}
