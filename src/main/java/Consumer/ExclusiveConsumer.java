package Consumer;

import Producer.Producer;
import org.apache.pulsar.client.api.*;
import Config.AppConfig;

public class ExclusiveConsumer {
    public static void main(String[] args) throws PulsarClientException {
        PulsarClient pulsarClient = PulsarClient.builder()
                                    .serviceUrl(AppConfig.SERVICE_URL)
                                    .build();
        Consumer consumer = pulsarClient.newConsumer()
                .topic(AppConfig.TOPIC)
                .consumerName("simple-consumer")
                .subscriptionName("test-subs")
                .subscriptionType(SubscriptionType.Exclusive)
                .subscribe();
        for(int i = 1; i<=60; i++){
            Message msg = consumer.receive();
            try{
                System.out.println("Received: " + new String(msg.getData()));
                consumer.acknowledge(msg);
            }catch (Exception e){
                consumer.negativeAcknowledge(msg);
            }
        }
        consumer.close();
        pulsarClient.close();
    }
}
