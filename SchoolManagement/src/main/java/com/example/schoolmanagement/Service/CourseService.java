package com.example.schoolmanagement.Service;

import com.example.schoolmanagement.Api.ApiException;
import com.example.schoolmanagement.Model.Course;
import com.example.schoolmanagement.Model.Teacher;
import com.example.schoolmanagement.Repository.CourseRepository;
import com.example.schoolmanagement.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    public List<Course> findAllCourses() {return courseRepository.findAll();}
    public void addCourse(Integer id,Course course) {
        Teacher teacher = teacherRepository.findTeacherById(id);
        if (teacher == null) {throw new ApiException("teacher not found");}

        course.setTeacher(teacher);
        courseRepository.save(course);
    }
    public void updateCourse(Integer id, Course course) {
        Course oldCourse = courseRepository.findCourseById(id);
        if (oldCourse == null) {throw new ApiException("course not found");}
        oldCourse.setName(course.getName());
        courseRepository.save(oldCourse);
    }
    public void deleteCourse(Integer id) {
        Course course = courseRepository.findCourseById(id);

        if (course == null) {throw new ApiException("course not found");}

        courseRepository.delete(course);
    }

    //Create endpoint that take course id and return the teacher name for that class
    public String courseTeacher(Integer course_id) {
        Course course = courseRepository.findCourseById(course_id);
        if (course == null) {throw new ApiException("course not found");}
        return "Teacher name for "+course.getName()+" is "+course.getTeacher().getName();
    }
}
