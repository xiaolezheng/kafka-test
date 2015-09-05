package com.weibangong.msg;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Date;
import java.util.Properties;
   
public class TestProducer {  
    public static void main(String[] args) {  
        long events = 90000;

        Properties props = new Properties();
        props.put("metadata.broker.list", "127.0.0.1:9093");
        props.put("serializer.class", "kafka.serializer.StringEncoder"); //默认字符串编码消息
        props.put("partitioner.class", "com.weibangong.msg.TenantIdPartitioner");
        props.put("request.required.acks", "1");  
   
        ProducerConfig config = new ProducerConfig(props);  
   
        Producer<String, String> producer = new Producer<String, String>(config);  
   
        for (long nEvents = 0; nEvents < events; nEvents++) {   
               long runtime = new Date().getTime();
               String ip = "0";
               if(nEvents %5 ==0) {
                   ip = "1";
               }
               String msg = "分区，"+ip + ", "+runtime + "," +nEvents+ ",";
               KeyedMessage<String, String> data = new KeyedMessage<String, String>("my-topic", ip, msg);
               producer.send(data);  
        }  
        producer.close();  
    }
}  
