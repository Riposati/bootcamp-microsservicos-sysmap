package com.sysmap.mslearningstudent.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService{
	
	@Value("${topic.name.producer}")
	private String topicName;
	
	private final KafkaTemplate<String, String> kafkaTemplate;
	
	public void send(String message) {
		
		kafkaTemplate.send(topicName,message);
	}

}
