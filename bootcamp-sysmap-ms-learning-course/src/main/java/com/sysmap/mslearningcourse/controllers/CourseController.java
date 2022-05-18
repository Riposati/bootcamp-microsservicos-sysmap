package com.sysmap.mslearningcourse.controllers;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sysmap.mslearningcourse.services.CourseDtoInput;
import com.sysmap.mslearningcourse.services.CourseServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "course")
@RequestMapping("/api/v1/course")

public class CourseController {

	private final CourseServiceImpl courseService;

	@Autowired
	public CourseController(CourseServiceImpl courseService) {
		this.courseService = courseService;
	}
	
	@PostMapping
	@ApiOperation(value = "Salva um curso")
	public UUID createCourse(@RequestBody CourseDtoInput courseDto) {
		return this.courseService.createCourse(courseDto.transformToObject(courseDto.getCourseName()));
	}
	
	@GetMapping
	@ApiOperation(value = "Recupera um curso ou todos os cursos cadastrados no Mongo")
	public ResponseEntity<?> searchCourses(@RequestParam("courseId") Optional<UUID> courseId) {
	    
		if(!courseId.isPresent()) {
	        return new ResponseEntity<>(courseService.getAllCourses(), HttpStatus.OK);
	    }
	   
	    return new ResponseEntity<>(courseService.getOneCourse(courseId.get()), HttpStatus.OK);
	}
}
