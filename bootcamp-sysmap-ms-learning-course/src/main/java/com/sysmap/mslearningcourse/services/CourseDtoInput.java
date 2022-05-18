package com.sysmap.mslearningcourse.services;

import java.time.LocalDateTime;
import java.util.UUID;

import com.sysmap.mslearningcourse.domain.Course;

import lombok.Data;

@Data

public class CourseDtoInput {

	private String courseName;
	
	public Course transformToObject(String courseName){
	    return new Course(UUID.randomUUID(), courseName, true, LocalDateTime.now());
	}
}
