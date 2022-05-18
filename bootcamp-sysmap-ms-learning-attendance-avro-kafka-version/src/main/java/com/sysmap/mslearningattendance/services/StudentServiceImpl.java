package com.sysmap.mslearningattendance.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sysmap.mslearningattendance.data.StudentRepository;
import com.sysmap.mslearningattendance.domain.Student;

@Service
public class StudentServiceImpl implements StudentService {

	private final StudentRepository studentRepository;
	
	@Autowired
	public StudentServiceImpl(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@Override
	public Student validadeStudent(UUID studentId) {
		
		Student student = this.studentRepository.findByStudentId(studentId);
		return student;
	}
}
