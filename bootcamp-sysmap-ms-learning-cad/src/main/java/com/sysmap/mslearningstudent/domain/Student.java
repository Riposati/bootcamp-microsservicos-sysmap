package com.sysmap.mslearningstudent.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "student")
@Data
public class Student {

	@Id
	private String id;
	
	private UUID studentId;
	private String firstName;
	private String lastName;
	private String document;
	private LocalDateTime birthdate;
	private UUID courseId;
	private Boolean status;
	private LocalDateTime createdOn;
	
	public Student() {
	}

	public Student(UUID studentId, String firstName, String lastName, String document, LocalDateTime birthdate,
			UUID courseId, boolean status, LocalDateTime createdOn) {
		this.studentId = studentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.document = document;
		this.birthdate = birthdate;
		this.courseId = courseId;
		this.status = status;
		this.createdOn = createdOn;
	}
	
	@Override
	public String toString() {
		return this.getFirstName() + " " + this.getLastName();
	}
}
