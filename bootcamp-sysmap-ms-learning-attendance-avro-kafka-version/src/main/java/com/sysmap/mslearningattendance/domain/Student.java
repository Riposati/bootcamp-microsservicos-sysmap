package com.sysmap.mslearningattendance.domain;

import java.util.UUID;

import javax.persistence.Id;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import lombok.Data;

@Data
@RedisHash("Student")

public class Student {

	@Id
	private String id;
	
	@Indexed
	private UUID studentId;
	
	private String fullName;
	private UUID courseId;
	
	public Student() {
	}

	public Student(UUID studentId, String fullName, UUID courseId) {

		this.studentId = studentId;
		this.fullName = fullName;
		this.courseId = courseId;
	}
}
