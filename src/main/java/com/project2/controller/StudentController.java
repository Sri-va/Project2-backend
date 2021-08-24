package com.project2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project2.entity.Student;
import com.project2.models.Login;
import com.project2.service.StudentService;

@RestController
@CrossOrigin(origins = "*")
public class StudentController {
	
	@Autowired
    private StudentService service;

    @PostMapping("/addStudent")
    public Student addStudent(@RequestBody Student student) {
        return service.saveStudent(student);
    }

    @PostMapping("/addStudents")
    public List<Student> addStudents(@RequestBody List<Student> students) {
        return service.saveStudents(students);
    }

    @GetMapping("/students")
    public List<Student> findAllStudents() {
        return service.getStudents();
    }

    @GetMapping("/studentById/{id}")
    public Student findStudentById(@PathVariable int id) {
        return service.getStudentById(id);
    }
    @GetMapping("/studentByEmail/{email}")
    public Student findStudentByEmail(@PathVariable String email) {
        return service.getStudentByEmail(email);
    }
    @GetMapping("/allstudentByCourse/{course}")
    public List<Student> findAllStudentByCourse(@PathVariable String course) {
        return service.getStudentsByCourse(course);
    }
    
    @PutMapping("/updateStudent")
    public Student updateStudent(@RequestBody Student student) {
        return service.updateStudent(student);
    }

    @DeleteMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable int id) {
        return service.deleteStudent(id);
    }
    
    @PostMapping("/loginStudent")
    public Student login(@RequestBody Login login) {
    	System.out.println(login);
    	Student curr_Stud = service.getStudentByEmail(login.getEmail());
    	System.out.println(curr_Stud);
    	String enteredPass = login.getPass();
    	System.out.println(enteredPass);
    	String userPass = curr_Stud.getPass();
    	System.out.println(userPass);
    	if(enteredPass.equals(userPass)) {
    		System.out.println("if loop entered");
    		return service.getStudentByEmail(curr_Stud.getEmail());
    	}
    	else {
    		return null;
    	}
    	
    }

}
