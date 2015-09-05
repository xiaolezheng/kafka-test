package com.weibangong.msg;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.message.MessageAndMetadata;

public class ConsumerTest implements Runnable {  
    private KafkaStream m_stream;  
    private int m_threadNumber;  
   
    public ConsumerTest(KafkaStream a_stream, int a_threadNumber) {  
        m_threadNumber = a_threadNumber;  
        m_stream = a_stream;  
    }  
   
    public void run() {
        ConsumerIterator<byte[], byte[]> it = m_stream.iterator();
        while (it.hasNext()) {
            MessageAndMetadata<byte[], byte[]> messageAndMetadata = it.next();
            System.out.println("Thread " + m_threadNumber + ": " + messageAndMetadata.message() + ":" +messageAndMetadata.partition());
        }

        System.out.println("Shutting down Thread: " + m_threadNumber);
    }  
}  