package com.sysmap.mslearningattendance.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sysmap.mslearningattendance.data.AttendanceRepository;
import com.sysmap.mslearningattendance.domain.Attendance;
import com.sysmap.mslearningattendance.domain.Student;

@Service
public class AttendanceServiceImpl implements AttendanceService {

	private final AttendanceRepository attendanceRepository;

	@Autowired
	public AttendanceServiceImpl(AttendanceRepository attendanceRepository) {
		this.attendanceRepository = attendanceRepository;
	}

	@Override
	public void registerStudentAttendance(Student student, Boolean attendanceStatus) {

		if(student != null && !this.searchCourseUsingId(student.getCourseId()).equals("")) {
			
			Attendance attendance = new Attendance(UUID.randomUUID(), student.getStudentId(), student.getCourseId(), LocalDateTime.now(), attendanceStatus);
			this.attendanceRepository.save(attendance);
		}
		
		else
			throw new IllegalStateException("Estudante ou curso informado não existe!");
	}

	private String searchCourseUsingId(UUID courseId) {

		String url = "http://localhost:8081/api/v1/course/?courseId=" + courseId;
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(url, String.class);
		return result;
	}
	
	private String takeCourseName(Student student) throws JsonProcessingException, JsonMappingException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(this.searchCourseUsingId(student.getCourseId()));			
		String courseName = jsonNode.get("courseName").asText();
		return courseName;
	}

	public StudentDtoOutput getAttendancesByStudent(Student student) throws JsonMappingException, JsonProcessingException {
		
		List<Attendance> attendences = this.attendanceRepository.findAttendancesByStudentId(student.getStudentId());
		
		StudentDtoOutput studentDtoOutput = new StudentDtoOutput(student.getFullName(), this.takeCourseName(student), attendences);
		return studentDtoOutput;
	}
}
