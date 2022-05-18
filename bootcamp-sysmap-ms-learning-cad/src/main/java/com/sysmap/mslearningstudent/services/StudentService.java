package com.sysmap.mslearningstudent.services;

import java.util.List;
import java.util.UUID;

import com.sysmap.mslearningstudent.domain.Student;

public interface StudentService {

	public UUID createStudent(Student course);
	public StudentDtoOutput getStudent(UUID StudentId);
	public List<Student> showAllStudents();
}
