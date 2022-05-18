package com.sysmap.mslearningcourse.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.sysmap.mslearningcourse.domain.Course;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CourseDtoOutput {

	private String courseName;
	private UUID courseId;
	private boolean status;
	
	public CourseDtoOutput(String courseName, UUID courseId, boolean courseStatus) {
		
		this.courseName = courseName;
		this.courseId = courseId;
		this.status = courseStatus;
	}
	
	public List<CourseDtoOutput> getAllCourses(List<Course> courses){
		
		List<CourseDtoOutput> courseDtoOutputList = new ArrayList<CourseDtoOutput>();
		
		for(Course course : courses) {
			courseDtoOutputList.add(new CourseDtoOutput(course.getCourseName(), course.getCourseId(), course.getStatus()));
		}
		return courseDtoOutputList;
	}
}
