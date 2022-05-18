package com.sysmap.mslearningattendance.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sysmap.mslearningattendance.domain.Student;
import com.sysmap.mslearningattendance.services.AttendanceServiceImpl;
import com.sysmap.mslearningattendance.services.StudentDtoOutput;
import com.sysmap.mslearningattendance.services.StudentServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "attendance")
@RequestMapping("/api/v1/")

public class AttendanceController {

	private final AttendanceServiceImpl attendanceServiceImpl;
	private StudentServiceImpl studentServiceImpl;

	@Autowired
	public AttendanceController(AttendanceServiceImpl attendanceServiceImpl, StudentServiceImpl studentServiceImpl) {
		this.attendanceServiceImpl = attendanceServiceImpl;
		this.studentServiceImpl = studentServiceImpl;
	}
	
	@PostMapping("/{studentId}/attendance")
	@ApiOperation(value = "Registra um comparecimento")
	public HttpStatus registerStudentAttendance(@PathVariable("studentId") UUID studentId) {
		
		Student student = this.studentServiceImpl.validadeStudent(studentId);	
		
		this.attendanceServiceImpl.registerStudentAttendance(student, true);
		
		return HttpStatus.OK;
	}
	
	@GetMapping("/{studentId}/attendances")
	@ApiOperation(value = "Recupera os estudantes com os comparecimentos")
	public StudentDtoOutput getAttendancesByStudent(@PathVariable("studentId") UUID studentId) throws JsonMappingException, JsonProcessingException {
		
		Student student = this.studentServiceImpl.validadeStudent(studentId);
		return this.attendanceServiceImpl.getAttendancesByStudent(student);
	}
}
