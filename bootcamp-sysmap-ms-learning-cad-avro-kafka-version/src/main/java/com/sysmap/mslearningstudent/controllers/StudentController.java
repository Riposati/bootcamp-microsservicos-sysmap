package com.sysmap.mslearningstudent.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sysmap.mslearningstudent.domain.Student;
import com.sysmap.mslearningstudent.services.StudentDtoInput;
import com.sysmap.mslearningstudent.services.StudentDtoOutput;
import com.sysmap.mslearningstudent.services.StudentServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "student")
@RequestMapping("/api/v1/student")

public class StudentController {

	private final StudentServiceImpl studentService;

	@Autowired
	public StudentController(StudentServiceImpl studentService) {
		this.studentService = studentService;
	}
	
	@PostMapping
	@ApiOperation(value = "Salva um Estudante")
	public UUID createStudent(@RequestBody StudentDtoInput studentDto) {
		return this.studentService.createStudent(studentDto.transformToObject(studentDto.getStudentName()));
	}
	
	@GetMapping("/{studentId}")
	@ApiOperation(value = "Obtém um Estudante")
	public StudentDtoOutput getStudent(@PathVariable("studentId") UUID studentId) {
		return studentService.getStudent(studentId);
	}
	
	@GetMapping
	@ApiOperation(value = "Retorna todos os estudantes")
	public List<Student> showAllStudents() {
		return studentService.showAllStudents();
	}
}
