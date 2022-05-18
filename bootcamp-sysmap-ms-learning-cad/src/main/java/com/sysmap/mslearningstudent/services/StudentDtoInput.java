package com.sysmap.mslearningstudent.services;

import java.time.LocalDateTime;
import java.util.UUID;

import com.sysmap.mslearningstudent.domain.Student;

import lombok.Data;

@Data

public class StudentDtoInput {

	private String studentName;
	private String studentLastName;
	private String studentDocument;
	private LocalDateTime studentBirthDate;
	private UUID courseId;
	
	public Student transformToObject(String studentName){
	    return new Student(UUID.randomUUID(), studentName, studentLastName, studentDocument, studentBirthDate, courseId, true, LocalDateTime.now());
	}
}
