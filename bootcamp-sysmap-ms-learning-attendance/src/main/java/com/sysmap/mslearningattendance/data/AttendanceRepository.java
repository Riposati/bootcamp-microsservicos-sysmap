package com.sysmap.mslearningattendance.data;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sysmap.mslearningattendance.domain.Attendance;

@Repository
public interface AttendanceRepository extends CrudRepository <Attendance, String> {
	
	public List<Attendance> findAttendancesByStudentId(UUID studentId);
}
