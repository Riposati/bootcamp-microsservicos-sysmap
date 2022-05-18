package com.sysmap.mslearningattendance.data;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sysmap.mslearningattendance.domain.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, String> {

	public Student findByStudentId(UUID studentId);
}
