package com.sysmap.mslearningattendance.services;

import java.util.UUID;

import com.sysmap.mslearningattendance.domain.Student;

public interface StudentService {

	public Student validadeStudent(UUID studentId);
}
