
public class KafkaConsumerDemo implements KafkaProperties {
    public static void main(String[] args) {

        Consumer consumerThread = new Consumer(topic);
        consumerThread.start();

    }
}