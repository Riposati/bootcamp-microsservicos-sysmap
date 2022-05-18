package com.sysmap.mslearningstudent.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sysmap.mslearningstudent.data.StudentRepository;
import com.sysmap.mslearningstudent.domain.CreatedStudentEvent;
import com.sysmap.mslearningstudent.domain.Student;
import com.sysmap.mslearningstudent.tratamentoerros.ApiException;

@Service
public class StudentServiceImpl implements StudentService {

	private final StudentRepository studentRepository;
	private final EventServiceImpl eventService; 
	
	@Autowired
	public StudentServiceImpl(StudentRepository studentRepository, EventServiceImpl eventService) {
		this.studentRepository = studentRepository;
		this.eventService = eventService;
	}

	@Override
	public UUID createStudent(Student student) {
		if(!this.searchCourseUsingId(student.getCourseId()).equals("")) {
			
			Optional<Student> studentOptional = this.studentRepository.findStudentByDocument(student.getDocument());
			
			if(studentOptional.isPresent()) {
				throw new IllegalStateException("Estudante já cadastrado!");
			}
			
			this.studentRepository.save(student);
			
			this.prepareEventForKafka(student);
			
			return student.getStudentId();
			
		}else 
			throw new IllegalStateException("Curso não existe!");
	}

	private void prepareEventForKafka(Student student) {
		CreatedStudentEvent event = new CreatedStudentEvent(student.getStudentId(), student.toString(), student.getCourseId());
		
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			
			String eventJson = objectMapper.writeValueAsString(event);
			this.eventService.send(eventJson);
											
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
		}
	}
	
	private String searchCourseUsingId(UUID courseId) {
		
		String url = "http://localhost:8081/api/v1/course/?courseId=" + courseId;
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(url, String.class);
		return result;
	}

	@Override
	public StudentDtoOutput getStudent(UUID studentId) {
		
		try {
			
			StudentDtoOutput studentOutput = studentOutputFormat(studentId);
			return studentOutput;
			
		} catch (ApiException apiException) {
			throw apiException;
		} catch (Exception exception) {
			throw new ApiException(exception.getMessage(), exception);
		}
	}

	private StudentDtoOutput studentOutputFormat(UUID studentId) throws JsonProcessingException, JsonMappingException {
		
		Student student = this.studentRepository.findByStudentId(studentId);		
		StudentDtoOutput studentOutput = new StudentDtoOutput(student.toString(),student.getDocument(),student.getBirthdate(),takeCourseName(student), student.getStatus());
		return studentOutput;
	}

	private String takeCourseName(Student student) throws JsonProcessingException, JsonMappingException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(this.searchCourseUsingId(student.getCourseId()));			
		String courseName = jsonNode.get("courseName").asText();
		return courseName;
	}

	@Override
	public List<Student> showAllStudents() {
		return this.studentRepository.findAll();
	}
}
