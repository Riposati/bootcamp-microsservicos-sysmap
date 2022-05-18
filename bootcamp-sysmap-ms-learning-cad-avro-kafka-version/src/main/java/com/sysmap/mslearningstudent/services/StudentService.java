package com.sysmap.mslearningstudent.services;

import java.util.List;
import java.util.UUID;

import com.avro.student.Students;
import com.sysmap.mslearningstudent.domain.Student;
import org.apache.kafka.clients.producer.ProducerRecord;

public interface StudentService {

    String topic();

    ProducerRecord<String, Students> createProducerRecord(Students students);

    void send(Student student);

    public UUID createStudent(Student course);
	public StudentDtoOutput getStudent(UUID StudentId);
	public List<Student> showAllStudents();
}
