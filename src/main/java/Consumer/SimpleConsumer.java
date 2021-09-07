package Consumer;

import Model.User;
import netscape.javascript.JSObject;
import org.apache.pulsar.client.api.*;
import Config.AppConfig;
import org.apache.pulsar.client.impl.schema.JSONSchema;

public class SimpleConsumer {
    public static void main(String[] args) throws PulsarClientException {
        PulsarClient pulsarClient = PulsarClient.builder()
                                    .serviceUrl(AppConfig.SERVICE_URL)
                                    .build();
        Consumer consumer = pulsarClient.newConsumer()
                .topic(AppConfig.TOPIC)
                .consumerName("simple-consumer")
                .subscriptionName("test-subs")
                .subscribe();
        while (true){
            Message<User> msg = consumer.receive();
            System.out.println("Received: "+ "Key: " +msg.getKey() + "Value " +msg.getData());
            try{

                consumer.acknowledge(msg);
            }catch (Exception e){
                consumer.negativeAcknowledge(msg);
            }
        }

    }
}
