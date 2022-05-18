package com.sysmap.mslearningattendance.services;

import com.avro.student.Students;
import com.sysmap.mslearningattendance.domain.Student;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.sysmap.mslearningattendance.data.StudentRepository;

import java.util.UUID;

@Service
@Slf4j
public class EventServiceImpl implements EventService {
	
	private StudentRepository studentRepository;
	
	@Autowired
	public EventServiceImpl(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	
//	@Value("${topic.name.consumer}")
//    private String topicName;
//
//    @KafkaListener(topics = "${topic.name.consumer}", groupId = "group_id")
//    public void consume(ConsumerRecord<String, String> payload){
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//			Student student = objectMapper.readValue(payload.value(), Student.class);
//			studentRepository.save(student);
//
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		}
//    }

	@Override
	@KafkaListener(topics = "student-avro",groupId = "group-1",containerFactory = "kafkaListenerContainerFactory")
	public void listen(ConsumerRecord<String, Students> payload) {
		Students student = payload.value();
		Student entity = new Student();
		entity.setCourseId(UUID.fromString(student.getCourseId()));
		entity.setStudentId(UUID.fromString(student.getStudentId()));
		entity.setFullName(student.getFullName());
		studentRepository.save(entity);
		log.info("{}",payload.value());
	}
}
