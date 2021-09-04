package Producer;
import Config.AppConfig;

import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;

import java.security.cert.CRLException;
import java.util.concurrent.TimeUnit;

public class Producer {
    public static void main(String[] args) throws PulsarClientException, InterruptedException {
        PulsarClient pulsarClient = PulsarClient.builder()
                                    .serviceUrl(AppConfig.SERVICE_URL)
                                    .build();
        org.apache.pulsar.client.api.Producer producer = pulsarClient.newProducer(Schema.STRING)
                            .topic(AppConfig.TOPIC)
                            .create();
        for (int i = 1; i<=90 ; i++){
            producer.newMessage().key("key" + i).value("value" + i).send();
            TimeUnit.SECONDS.sleep(1);
        }
        producer.close();
        pulsarClient.close();

    }
}
