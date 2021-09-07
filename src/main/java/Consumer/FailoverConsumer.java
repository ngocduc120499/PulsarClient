package Consumer;

import Config.AppConfig;
import Config.ConsumerThread;
import org.apache.pulsar.client.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import Config.AppConfig;
public class FailoverConsumer {
    public static void main(String[] args) throws PulsarClientException, InterruptedException {
        PulsarClient pulsarClient = PulsarClient.builder()
                .serviceUrl(AppConfig.SERVICE_URL)
                .build();
        List<ConsumerThread> consumerThreads = IntStream.of(1,2).mapToObj(id-> {
            ConsumerThread consumerThread = new ConsumerThread(
                    pulsarClient, AppConfig.TOPIC
                    , "test-subs"
                    , "Failover-" + id
                    , SubscriptionType.Failover);
            consumerThread.addDelay(250);
            return consumerThread;
        }).collect(Collectors.toList());
        consumerThreads.forEach(Thread::start);
        for(int i = 1; i<consumerThreads.size();i++){
            Thread.sleep(10000);
            consumerThreads.get(i - 1).disconectConsumer();
        }
    }
}
