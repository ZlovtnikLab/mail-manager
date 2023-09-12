package de.rcs.mailcontroller.mailcontroller.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class Mail extends RouteBuilder {

    @Autowired
    Environment env;

    @Override
    public void configure() throws Exception {

            from("direct:sendMail")
                    .process( exchange -> {
                      exchange.getIn().setBody(exchange.getIn().getBody(String.class));
                    }).to("smtps://smtp.gmail.com:465" +
                            "?username=zlovtnikrcs@gmail.com" +
                            "&password=bcyx zmmv mzyk vtgx" +
                            "&debugMode=true" +
                            "&javaMailProperties.mail.smtps.auth=true" +
                            "&javaMailProperties.mail.smtps.starttls.enable=true")
                    .process(exchange -> {
                        System.out.println(exchange.getIn().getBody());
                    });

            from("jetty:http://0.0.0.0:8081")
                    .to("direct:sendMail");

    }
}
