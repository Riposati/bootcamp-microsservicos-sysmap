package com.sysmap.mslearningstudent.domain;

import java.util.UUID;

public class CreatedStudentEvent {

	public UUID studentId;
	public String fullName;
	public UUID courseId;
	
	public CreatedStudentEvent(UUID studentId, String fullName, UUID courseId) {
		this.studentId = studentId;
		this.fullName = fullName;
		this.courseId = courseId;
	}	
}
