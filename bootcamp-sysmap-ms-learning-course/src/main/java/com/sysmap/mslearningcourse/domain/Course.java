package com.sysmap.mslearningcourse.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "courses")
@Data
public class Course {

	@Id
	private String id;
	
	private UUID courseId;
	private String courseName;
	private Boolean status;
	private LocalDateTime createdOn;
	
	public Course() {
	}

	public Course(UUID courseId, String courseName, boolean status, LocalDateTime createdOn) {
		this.courseId = courseId;
		this.courseName = courseName;
		this.status = status;
		this.createdOn = createdOn;
	}
}
