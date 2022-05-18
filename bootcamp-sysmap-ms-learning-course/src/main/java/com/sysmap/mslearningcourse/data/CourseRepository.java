package com.sysmap.mslearningcourse.data;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.sysmap.mslearningcourse.domain.Course;

@Repository
public interface CourseRepository extends MongoRepository<Course, String> {

	@Query(value = "{}", fields="{'courseId' : 1, 'courseName' : 1, 'status' : 1}")
	public List<Course> findAllSpecific();
	
	@Query(value = "{ 'courseId': ?0 }", fields="{'courseId' : 1, 'courseName' : 1, 'status' : 1}")
	public Course findByCourseId(UUID courseId);
		
	public Optional<Course> findByCourseName(String courseName);
}
