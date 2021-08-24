package com.project2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project2.entity.Student;
import com.project2.entity.Teacher;
import com.project2.repository.TeacherRepository;

@Service
public class TeacherService {
	@Autowired
    private TeacherRepository repository;
	
	@Autowired
	private EmailService emailService;
	
    public Teacher saveTeacher(Teacher teacher) {
    	String message = "hi, your login details are, UserName:  "+teacher.getEmail()+"  Password:  "+teacher.getPass();
    	emailService.sendSimpleEmail(teacher.getEmail(), message, "your login credentials");
        return repository.save(teacher);
    }

    public List<Teacher> saveTeachers(List<Teacher> teachers) {
        return repository.saveAll(teachers);
    }

    public List<Teacher> getTeachers() {
        return repository.findAll();
    }

    public Teacher getTeacherById(int id) {
        return repository.findById(id).orElse(null);
    }
    
    public Teacher getTeacherByEmail(String email) {
        return repository.findByEmail(email);
    }

    public String deleteTeacher(int id) {
        repository.deleteById(id);
        return "Teacher removed !! " + id;
    }

    public Teacher updateTeacher(Teacher teacher) {
        Teacher existingTeacher = repository.findById(teacher.getTeacherId()).orElse(null);
        existingTeacher.setFirstName(teacher.getFirstName());
        existingTeacher.setLastName(teacher.getLastName());
        existingTeacher.setEmail(teacher.getEmail());
        existingTeacher.setPhno(teacher.getPhno());
        existingTeacher.setCourse(teacher.getCourse());
        existingTeacher.setUrl(teacher.getUrl());
        existingTeacher.setUrlDate(teacher.getUrlDate());

        return repository.save(existingTeacher);
    }
    
    
}
