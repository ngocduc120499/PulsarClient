package Consumer;

import Model.Ticket;
import Producer.Producer;
import org.apache.pulsar.client.api.*;
import Config.AppConfig;
import org.apache.pulsar.client.impl.schema.JSONSchema;

import java.util.List;

public class ExclusiveConsumer {
    public static void main(String[] args) throws PulsarClientException {
        PulsarClient pulsarClient = PulsarClient.builder()
                                    .serviceUrl(AppConfig.SERVICE_URL)
                                    .build();
        Consumer<Ticket> consumer = pulsarClient.newConsumer(Schema.JSON(Ticket.class))
                .topic(AppConfig.TOPIC)
                .consumerName("simple-consumer")
                .subscriptionName("test-subs")
                .subscriptionType(SubscriptionType.Exclusive)
                .subscribe();
        while (true){
            Message<Ticket> msg = consumer.receive();
            try{
                System.out.println("Received: " +new String(msg.getData()));
                consumer.acknowledge(msg);
            }catch (Exception e){
                consumer.negativeAcknowledge(msg);
            }
        }

    }
}
