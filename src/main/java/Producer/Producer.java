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
        producer.newMessage().key("key-1").value("message-1-1").send();
        producer.newMessage().key("key-1").value("message-1-2").send();
        producer.newMessage().key("key-1").value("message-1-3").send();
        producer.newMessage().key("key-2").value("message-2-1").send();
        producer.newMessage().key("key-2").value("message-2-2").send();
        producer.newMessage().key("key-2").value("message-2-3").send();
        producer.newMessage().key("key-3").value("message-3-1").send();
        producer.newMessage().key("key-3").value("message-3-2").send();
        producer.newMessage().key("key-4").value("message-4-1").send();
        producer.newMessage().key("key-4").value("message-4-2").send();
        producer.close();
        pulsarClient.close();

    }
}
