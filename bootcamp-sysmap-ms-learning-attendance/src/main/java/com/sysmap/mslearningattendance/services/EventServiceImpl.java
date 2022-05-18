package com.sysmap.mslearningattendance.services;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sysmap.mslearningattendance.data.StudentRepository;
import com.sysmap.mslearningattendance.domain.Student;

@Service
public class EventServiceImpl implements EventService {
	
	private StudentRepository studentRepository;
	
	@Autowired
	public EventServiceImpl(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	
	@Value("${topic.name.consumer}")
    private String topicName;

    @KafkaListener(topics = "${topic.name.consumer}", groupId = "group_id")
    public void consume(ConsumerRecord<String, String> payload){
    	
        ObjectMapper objectMapper = new ObjectMapper();
        try {
			Student student = objectMapper.readValue(payload.value(), Student.class);			
			studentRepository.save(student);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    }
}
