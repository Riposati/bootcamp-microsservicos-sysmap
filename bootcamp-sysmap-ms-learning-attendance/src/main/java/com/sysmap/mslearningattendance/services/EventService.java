package com.sysmap.mslearningattendance.services;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface EventService {
	
	public void consume(ConsumerRecord<String, String> payload);
}
