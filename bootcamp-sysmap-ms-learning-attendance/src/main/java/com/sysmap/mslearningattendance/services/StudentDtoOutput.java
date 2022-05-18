package com.sysmap.mslearningattendance.services;

import java.util.List;

import com.sysmap.mslearningattendance.domain.Attendance;

import lombok.Data;

@Data
public class StudentDtoOutput {

	private String studentFullName;
	private String courseName;
	private List<Attendance> attendances;
	
	public StudentDtoOutput(String studentFullName,
			String courseName, List<Attendance> attendances) {
		this.studentFullName = studentFullName;
		this.courseName = courseName;
		this.attendances = attendances;
	}
}
