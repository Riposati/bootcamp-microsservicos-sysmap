package com.sysmap.mslearningcourse.services;

import java.util.List;
import java.util.UUID;

import com.sysmap.mslearningcourse.domain.Course;

public interface CourseService {

	public UUID createCourse(Course course);
	public List<CourseDtoOutput> getAllCourses();
	public CourseDtoOutput getOneCourse(UUID id);
}
