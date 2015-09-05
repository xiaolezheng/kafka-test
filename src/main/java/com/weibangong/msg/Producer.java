package com.weibangong.msg;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageQueue;
import com.google.common.collect.Maps;

import java.util.Map;

public class Producer {
	public static void main(String[] args){
		long start = System.currentTimeMillis();
		DefaultMQProducer producer = new DefaultMQProducer("Producer");
        producer.setDefaultTopicQueueNums(1000);


		producer.setNamesrvAddr("192.168.4.140:9876");
		try {
			producer.start();

			Message msg = new Message("PushTopic", 
					"push",
					"1",
					"Just for test.".getBytes());
			
			SendResult result = producer.send(msg);
			System.out.println("id:" + result.getMsgId() +
					" result:" + result.getSendStatus());

			MessageQueue queue = new MessageQueue("PushTopic", "broker-a", 0);
			MessageQueue queue1 = new MessageQueue("PushTopic", "broker-a", 1);
			MessageQueue queue2 = new MessageQueue("PushTopic", "broker-a", 2);
			MessageQueue queue3 = new MessageQueue("PushTopic", "broker-a",3);
			//MessageQueue queue4 = new MessageQueue("PushTopic", "broker-a", 4);

			for(int i=0; i<200; i++) {
				String content = "just for test你好啊小朋友你的在干嘛呢，你真的听票两的的地方撒发的撒地方撒的撒打算发的撒短发 受到粉丝的发达舒服" + i;

				Map<String,String> body = Maps.newHashMap();
				body.put("clientId","0001");
				body.put("body",content);

				msg = new Message("PushTopic",
						"push",
						"2",
						content.getBytes());

				switch (i%5){
					case 0:
						result = producer.send(msg,queue);
						break;
					case 1:
						result = producer.send(msg,queue1);
						break;
					case 2:
						result = producer.send(msg,queue2);

						break;
					case 3:
						result = producer.send(msg,queue3);
						break;
					default:
						result = producer.send(msg,queue3);
						break;
				}


				//System.out.println("id:" + result.getMsgId() +
						//" result:" + result.getSendStatus());
			}
			
			msg = new Message("PullTopic", 
					"pull", 
					"1", 
					"Just for test dd.".getBytes());
			
			result = producer.send(msg);
			System.out.println("id:" + result.getMsgId() +
					" result:" + result.getSendStatus());

			long cost = System.currentTimeMillis() - start;
			System.out.println("cost: "+cost);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			producer.shutdown();
		}
	}
}
