package com.sysmap.mslearningattendance.services;

import com.sysmap.mslearningattendance.domain.Student;

public interface AttendanceService {

	public void registerStudentAttendance(Student student, Boolean attendanceStatus);
}
