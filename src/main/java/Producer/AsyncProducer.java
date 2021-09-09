package Producer;

import Interceptor.CustomAsyncProducerInter;
import Interceptor.CustomProducerInter;
import Model.Business;
import Model.User;
import Utils.UnitModel;
import org.apache.pulsar.client.api.BatcherBuilder;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.impl.schema.JSONSchema;
import Config.AppConfig;

import java.io.IOException;
import java.util.List;

public class AsyncProducer {
    public static void main(String[] args) throws IOException {
        List<User> listUser = UnitModel.loadUserData();

        // 1. Instantiate pulsar client
        PulsarClient pulsarClient = PulsarClient.builder()
                .serviceUrl(AppConfig.SERVICE_URL)
                .build();

        CustomAsyncProducerInter interceptor = new CustomAsyncProducerInter();
        // 2. Pulsar Producer
        Producer<User> producer = pulsarClient.newProducer(JSONSchema.of(User.class))
                .topic(AppConfig.TOPIC)
                .producerName("stock-ticker-producer")
                .intercept(interceptor)
                .enableBatching(true)
                .batcherBuilder(BatcherBuilder.KEY_BASED)
                .blockIfQueueFull(true)
                .maxPendingMessages(50000)
                .create();

        System.out.println("Running Producer work loop");
        // 3. Initiate a producer work loop
        for (User st: listUser) {
            producer.newMessage(JSONSchema.of(User.class))
                    .key(st.getName())
                    .value(st)
                    .sendAsync();
        }

        // add a shutdown hook to clear the resources
        Runtime.getRuntime()
                .addShutdownHook(new Thread(() -> {
                    System.out.println("Closing producer and pulsar client..");
                    try {
                        producer.close();
                        pulsarClient.close();
                    } catch (PulsarClientException e) {
                        e.printStackTrace();
                    }
                }));
    }
}
