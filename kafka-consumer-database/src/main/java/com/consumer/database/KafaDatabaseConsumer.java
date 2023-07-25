package com.consumer.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.consumer.database.entity.WikimediaData;
import com.consumer.database.repository.WikimediaRepository;

@Service
public class KafaDatabaseConsumer {

	private static final Logger logger = LoggerFactory.getLogger(KafaDatabaseConsumer.class);
	
	private WikimediaRepository dataRepository;
	
	
	public KafaDatabaseConsumer(WikimediaRepository repository) {
		this.dataRepository = repository;
	}

	@KafkaListener(topics = "wikimedia_recentchange",groupId = "myGroup")
	public void consume(String eventMessage) {
		logger.info(String.format("event message received: %s",eventMessage));
		WikimediaData wikimediaData = new WikimediaData();
		wikimediaData.setWikiEventData(eventMessage);
		dataRepository.save(wikimediaData);
	}
			
}
