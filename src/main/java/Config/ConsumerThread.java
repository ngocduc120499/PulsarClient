package Config;

import org.apache.pulsar.client.api.*;

import java.util.concurrent.atomic.AtomicInteger;

public class ConsumerThread extends Thread{
    private Consumer<byte[]> consumer;
    private final AtomicInteger totalMessages = new AtomicInteger();
    private int delay = 0;
    private boolean isRunning = true;
    public ConsumerThread(PulsarClient pulsarClient,
                          String topicName,
                          String subscriptionName,
                          String consumerName,
                          SubscriptionType subscriptionType) {
        try {
            this.consumer =  pulsarClient.newConsumer()
                    .topic(topicName)
                    .subscriptionName(subscriptionName)
                    .subscriptionType(subscriptionType)
                    .consumerName(consumerName)
                    .subscribe();
            registerShutdownHook();
        } catch (PulsarClientException e) {
            System.out.println(e);
        }
    }

    @Override
    public void run() {
        while (isRunning) {
            if (delay != 0) {
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Message msg = null;
            try {
                msg = consumer.receive();
                if (msg != null) {
                    consumer.acknowledge(msg);
                    System.out.println("[" + consumer.getConsumerName() + "] Received message: " + new String(msg.getData()));
                    System.out.println("[" + consumer.getConsumerName() + "] Total messages received: " + totalMessages.addAndGet(1));
                }
            } catch (PulsarClientException e) {
                consumer.negativeAcknowledge(msg);
            }
        }
        try {
            this.consumer.close();
            System.out.println(consumer.getConsumerName() + " disconnected");
        } catch (PulsarClientException e) {
            e.printStackTrace();
        }
    }
    public void addDelay(int delay){
        this.delay = delay;
    }
    public void disconectConsumer(){
        this.isRunning = false;
    }

    private void registerShutdownHook() {
        Runtime.getRuntime()
                .addShutdownHook(new Thread(() -> {
                    System.out.println("[" + consumer.getConsumerName() + "] Disconnecting..");
                    try {
                        consumer.close();
                    } catch (PulsarClientException e) {
                        e.printStackTrace();
                    }
                }));
    }
}
