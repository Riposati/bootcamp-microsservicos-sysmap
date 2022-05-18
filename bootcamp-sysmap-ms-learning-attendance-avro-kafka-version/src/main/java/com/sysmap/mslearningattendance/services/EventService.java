package com.sysmap.mslearningattendance.services;

import com.avro.student.Students;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

public interface EventService {

	void listen(ConsumerRecord<String, Students> payload);
	//public void consume(ConsumerRecord<String, String> payload);
}
