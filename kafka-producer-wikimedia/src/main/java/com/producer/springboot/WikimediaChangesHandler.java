package com.producer.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;

public class WikimediaChangesHandler implements EventHandler{

	private static final Logger logger = LoggerFactory.getLogger(WikimediaChangesHandler.class);
	private KafkaTemplate<String, String> kafkaTemplate;
	private String topicName;
	
	
	
	public WikimediaChangesHandler(KafkaTemplate<String, String> kafkaTemplate, String topicName) {
		this.kafkaTemplate = kafkaTemplate;
		this.topicName = topicName;
	}

	@Override
	public void onOpen() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClosed() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMessage(String event, MessageEvent messageEvent) throws Exception {
		logger.info("Message: {}"+messageEvent.getData());
		
		kafkaTemplate.send(topicName,messageEvent.getData());
	}

	@Override
	public void onComment(String comment) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(Throwable t) {
		// TODO Auto-generated method stub
		
	}

}
