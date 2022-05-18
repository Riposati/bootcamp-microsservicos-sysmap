package com.sysmap.mslearningattendance.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Id;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@RedisHash("Attendance")
@NoArgsConstructor

public class Attendance {

	@Id
	private String id;
	
	private UUID attendanceId;
	
	@Indexed
	private UUID studentId;

	private UUID courseId;
	private LocalDateTime classDate;
	private Boolean attendanceStatus;
	
	public Attendance(UUID attendanceId, UUID studentId, UUID courseId, LocalDateTime classDate,
			Boolean attendanceStatus) {

		this.attendanceId = attendanceId;
		this.studentId = studentId;
		this.courseId = courseId;
		this.classDate = classDate;
		this.attendanceStatus = attendanceStatus;
	}
	
}
