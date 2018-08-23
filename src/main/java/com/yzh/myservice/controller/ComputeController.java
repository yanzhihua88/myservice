package com.yzh.myservice.controller;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest")
public class ComputeController {

	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private DiscoveryClient client;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public Integer add(@RequestParam Integer a, @RequestParam Integer b) {
		ServiceInstance instance = client.getLocalServiceInstance();
		Integer r = a + b;
		logger.info("/add, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + r);
		return r;
	}

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@RequestMapping(value = "/send", method = RequestMethod.GET)
	public String send() {
		kafkaTemplate.send("test", "key", "aabbccdd");
//		kafkaTemplate.send("test", 0, "aabbcc");
        logger.info("发送kafka成功.");
        return "发送kafka成功";
//		kafkaTemplate.send("myTopic1", "xiaojf1");
//		kafkaTemplate.send("myTopic2", "xiaojf2");

//		kafkaTemplate.metrics();

//		kafkaTemplate.execute(new KafkaOperations.ProducerCallback<String, String, Object>() {
//
//			@Override
//			public Object doInKafka(Producer<String, String> arg0) {
//				// TODO Auto-generated method stub
//				return null;
//			}
//		});
//
//		// 消息发送的监听器，用于回调返回信息
//		kafkaTemplate.setProducerListener(new ProducerListener<String, String>() {
//
//			@Override
//			public boolean isInterestedInSuccess() {
//				// TODO Auto-generated method stub
//				return false;
//			}
//
//			@Override
//			public void onError(String arg0, Integer arg1, String arg2, String arg3, Exception arg4) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void onSuccess(String arg0, Integer arg1, String arg2, String arg3, RecordMetadata arg4) {
//				// TODO Auto-generated method stub
//
//			}
//
//		});
	}

}