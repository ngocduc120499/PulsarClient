package Consumer;

import Config.AppConfig;
import Config.ConsumerThread;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.SubscriptionType;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.IntStream;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.SubscriptionType;
public class SharedConsumer {
    public static void main(String[] args) throws PulsarClientException{
        PulsarClient pulsarClient = PulsarClient.builder()
                .serviceUrl(AppConfig.SERVICE_URL)
                .build();
        IntStream.of(1,2).mapToObj(id-> {
            ConsumerThread consumerThread = new ConsumerThread(
                    pulsarClient, AppConfig.TOPIC
                    , "test-subs"
                    , "Shared-" + id
                    , SubscriptionType.Shared);
            return consumerThread;
        }).forEach(Thread::start);
    }
}
