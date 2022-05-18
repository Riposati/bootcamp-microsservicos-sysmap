package com.sysmap.mslearningstudent.data;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sysmap.mslearningstudent.domain.Student;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {

	Optional<Student> findStudentByDocument(String document);
	Student findByStudentId(UUID studentId);
}
