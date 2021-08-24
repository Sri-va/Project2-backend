package com.project2.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Student_Table")
public class Student {
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	private int stuId;
	private String firstName;
	private String lastName;
	private String email;
	private long phno;
	private int grade;
	private String pass;
	private String course;
	private String url;
	private Date urlDate;
}
