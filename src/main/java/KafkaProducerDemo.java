/**
 * Created by xiaolezheng on 15/9/1.
 */
public class KafkaProducerDemo implements KafkaProperties {
    public static void main(String[] args){
        Producer producerThread = new Producer(topic2, false);

        producerThread.start();
    }
}
