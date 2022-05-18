package com.sysmap.mslearningcourse.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sysmap.mslearningcourse.data.CourseRepository;
import com.sysmap.mslearningcourse.domain.Course;
import com.sysmap.mslearningcourse.tratamentoerros.ApiException;

@Service
public class CourseServiceImpl implements CourseService {

	private final CourseRepository courseRepository;

	@Autowired
	public CourseServiceImpl(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	@Override
	public UUID createCourse(Course course) {
		
		Optional<Course> courseOptional = courseRepository.findByCourseName(course.getCourseName());

		if (course.getCourseName().trim().equals("")) 
			throw new IllegalStateException("Não é possível inserir curso sem nome!");
		
		if (courseOptional.isPresent()) {
			throw new IllegalStateException("Curso já informado!");
		}
		
		this.courseRepository.save(course);
		
		return course.getCourseId();
	}

	@Override
	public List<CourseDtoOutput> getAllCourses() {

		try {
			CourseDtoOutput courseDtoOutput = new CourseDtoOutput();
			return courseDtoOutput.getAllCourses(this.courseRepository.findAllSpecific());
		} catch (ApiException apiException) {
			throw apiException;
		} catch (Exception exception) {
			throw new ApiException(exception.getMessage(), exception);
		}
	}

	@Override
	public CourseDtoOutput getOneCourse(UUID id) {

		try {
			Course course = this.courseRepository.findByCourseId(id);
			return new CourseDtoOutput(course.getCourseName(),course.getCourseId(), course.getStatus());
		} catch (ApiException apiException) {
			throw apiException;
		} catch (Exception exception) {
			throw new ApiException(exception.getMessage(), exception);
		}
	}
}
