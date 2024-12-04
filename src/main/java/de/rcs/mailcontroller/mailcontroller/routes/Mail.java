import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class Mail {
        public static void main(String[] args) throws Exception {
                CamelContext context = new DefaultCamelContext();

                context.addRoutes(new RouteBuilder() {
                        @Override
                        public void configure() {
                                from("amqp:queue:myQueue")
                                                .process(exchange -> {
                                                        System.out.println(exchange.getIn().getBody(String.class));
                                                });
                        }
                });

                context.start();
                Thread.sleep(5000);
                context.stop();
        }
}