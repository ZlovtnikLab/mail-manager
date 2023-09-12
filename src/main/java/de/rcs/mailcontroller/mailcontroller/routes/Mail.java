package de.rcs.mailcontroller.mailcontroller.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class Mail extends RouteBuilder {

    @Autowired
    Environment env;

    @Override
    public void configure() throws Exception {

            from("direct:sendMail")
                    .process( exchange -> {
                      exchange.getIn().setBody(exchange.getIn().getBody(String.class));
                    }).to("smtps://smtp.synchro.com.br?" +
                            "to=racasantos@icloud.com" +
                            "&subject=teste" +
                            "&from=contact@rclabs.de" +
                            "&username=rafael.cardoso@synchro.com.br" +
                            "&password=zuv9yfb*PNW.jrg3drd")
                    .process(exchange -> {
                        System.out.println(exchange.getIn().getBody());
                    });

            from("jetty:http://localhost:8081")
                    .to("direct:sendMail");

    }
}
