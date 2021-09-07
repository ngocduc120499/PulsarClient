package Producer;

import Config.AppConfig;
import Interceptor.CustomProducerInter;
import Model.User;
import org.apache.pulsar.client.api.*;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.impl.schema.JSONSchema;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import Utils.UnitModel;
public class ProducerDemo {

    public static void main(String[] args) throws PulsarClientException {
        List<User> listUsers = UnitModel.loadUserData();
        PulsarClient pulsarClient = PulsarClient.builder()
                .serviceUrl(AppConfig.SERVICE_URL)
                .build();
        CustomProducerInter interceptor = new CustomProducerInter();
        Producer<User> producer = pulsarClient.newProducer(JSONSchema.of(User.class))
                .topic(AppConfig.TOPIC)
                .producerName("producer")
                .intercept(interceptor)
                .create();
        System.out.println("Running producer work loop");
        long t1 = System.currentTimeMillis();
        for(User us : listUsers){
            producer.newMessage(JSONSchema.of(User.class))
                    .key(us.getName())
                    .value(us)
                    .send();
        }
        long t2 = System.currentTimeMillis();
        long totalTimeSeconds = TimeUnit.MILLISECONDS.toSeconds(t2 - t1);
        producer.flush();
        System.out.printf("Total messages produced: %s in %s seconds.%n", interceptor.totalMessageCount(), totalTimeSeconds);
        producer.close();
        pulsarClient.close();
    }
}
