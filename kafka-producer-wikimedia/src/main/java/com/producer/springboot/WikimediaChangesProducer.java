package com.producer.springboot;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;

@Service
public class WikimediaChangesProducer {

	@Value("${kafka-topic-name}")
	private String topicName;
	
	private static final Logger logger = LoggerFactory.getLogger(WikimediaChangesProducer.class);
	
	private KafkaTemplate<String,String> kafkaTemplate;

	public WikimediaChangesProducer(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void sendMessage() throws InterruptedException {
		//read real time stream data from wikimedia use event source
		 EventHandler eventHandler = new WikimediaChangesHandler(kafkaTemplate,topicName);
		 String url = "https://stream.wikimedia.org/v2/stream/recentchange";
		 EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(url));
		 EventSource eventSource = builder.build();
		 eventSource.start();
		 Thread.sleep(600000);
		 
	}
	
	
}
