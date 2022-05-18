package com.sysmap.mslearningstudent.services;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class StudentDtoOutput {

	private String studentFullName;
	private String studentDocument;
	private LocalDateTime studentBirthDate;
	private String courseName;
	private boolean status;
	
	public StudentDtoOutput(String studentFullName, String studentDocument, LocalDateTime studentBirthDate,
			String courseName, boolean status) {
		this.studentFullName = studentFullName;
		this.studentDocument = studentDocument;
		this.studentBirthDate = studentBirthDate;
		this.courseName = courseName;
		this.status = status;
	}
}
