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
import com.project2.entity.Teacher;
import com.project2.entity.Teacher;
import com.project2.models.Login;
import com.project2.models.MeetingUrl;
import com.project2.service.StudentService;
import com.project2.service.TeacherService;

@RestController
@CrossOrigin(origins = "*")
public class TeacherController {
	
	@Autowired
    private TeacherService service;
	@Autowired
	private StudentService stuser;

    @PostMapping("/addTeacher")
    public Teacher addTeacher(@RequestBody Teacher teacher) {
        return service.saveTeacher(teacher);
    }

    @PostMapping("/addTeachers")
    public List<Teacher> addTeachers(@RequestBody List<Teacher> teachers) {
        return service.saveTeachers(teachers);
    }

    @GetMapping("/teachers")
    public List<Teacher> findAllTeachers() {
        return service.getTeachers();
    }

    @GetMapping("/teacherById/{id}")
    public Teacher findTeacherById(@PathVariable int id) {
        return service.getTeacherById(id);
    }
    
    @GetMapping("/teacherByEmail/{email}")
    public Teacher findTeacherByEmail(@PathVariable String email) {
        return service.getTeacherByEmail(email);
    }

    @PutMapping("/updateTeacher")
    public Teacher updateTeacher(@RequestBody Teacher teacher) {
        return service.updateTeacher(teacher);
    }

    @DeleteMapping("/deleteTeacher/{id}")
    public String deleteTeacher(@PathVariable int id) {
        return service.deleteTeacher(id);
    }
    
    @PostMapping("/loginTeacher")
    public Teacher login(@RequestBody Login login) {
    	System.out.println(login);
    	Teacher curr_Teacher = service.getTeacherByEmail(login.getEmail());
    	System.out.println(curr_Teacher);
    	String enteredPass = login.getPass();
    	System.out.println(enteredPass);
    	String userPass = curr_Teacher.getPass();
    	System.out.println(userPass);
    	if(enteredPass.equals(userPass)) {
    		System.out.println("if loop entered");
    		return service.getTeacherByEmail(curr_Teacher.getEmail());
    	}
    	else {
    		return null;
    	}
    }
    @PostMapping("/meetingURL")
    public String meetingUrlrequest(@RequestBody MeetingUrl meetingurl) {
    	Teacher teacher1 = findTeacherById(meetingurl.getTeachID());
    	teacher1.setUrl(meetingurl.getMeetingURL());
    	teacher1.setUrlDate(meetingurl.getDate());
    	service.updateTeacher(teacher1);
    	List<Student> stud1 = stuser.getStudentsByCourse(teacher1.getCourse());
    	for(Student s:stud1) {
    		s.setUrl(meetingurl.getMeetingURL());
    		s.setUrlDate(meetingurl.getDate());
    		stuser.updateStudent(s);
    	}
		return null;
    	
    }
}
