package com.project2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project2.entity.Course;
import com.project2.entity.Student;
import com.project2.entity.Teacher;
import com.project2.repository.CourseRepository;
import com.project2.repository.StudentRepository;
import com.project2.repository.TeacherRepository;

@Service
public class StudentService {
	@Autowired
    private StudentRepository repository;
	@Autowired
	private TeacherRepository teacherRepository;
	@Autowired
    private CourseRepository courserepository;
	@Autowired
	private EmailService emailService;
	
    public Student saveStudent(Student student) {
    	String message = "hi, your login details are, UserName:  "+student.getEmail()+"  Password:  "+student.getPass();
    	emailService.sendSimpleEmail(student.getEmail(), message, "your login credentials");
    	Teacher teacher = teacherRepository.findByCourse(student.getCourse());
    	Course course =  new Course();
    	repository.save(student);
    	course.setCourseName(student.getCourse());
    	course.setStudentiId(student.getStuId());
    	course.setTeacherId(teacher.getTeacherId());
    	courserepository.save(course);
        return repository.save(student);
    }

    public List<Student> saveStudents(List<Student> students) {
        return repository.saveAll(students);
    }

    public List<Student> getStudents() {
        return repository.findAll();
    }

    public Student getStudentById(int id) {
        return repository.findById(id).orElse(null);
    }
    public Student getStudentByEmail(String email) {
        return repository.findByEmail(email);
    }
    
    public List<Student> getStudentsByCourse(String course){
    	return repository.findAllByCourse(course);
    }

    public String deleteStudent(int id) {
        repository.deleteById(id);
        return "Student removed !! " + id;
    }

    public Student updateStudent(Student student) {
        Student existingStudent = repository.findByEmail(student.getEmail());
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setPhno(student.getPhno());
        existingStudent.setGrade(student.getGrade());
        //existingStudent.setCourse(student.getCourse());
        existingStudent.setPass(student.getPass());
        existingStudent.setUrl(student.getUrl());
        existingStudent.setUrlDate(student.getUrlDate());

        return repository.save(existingStudent);
    }
}
