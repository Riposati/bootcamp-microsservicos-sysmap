package com.sysmap.mslearningattendance.services;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class AttendanceDtoOutput {

	private UUID courseId;
	private LocalDateTime classDate;	
	private boolean attendanceStatus;
	
	public AttendanceDtoOutput(UUID courseId,
			LocalDateTime classDate, boolean attendanceStatus) {
		
		this.courseId = courseId;
		this.classDate = classDate;
		this.attendanceStatus = attendanceStatus;
	}
}
