package com.test.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Teacher {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int tit;
	private String salary;
	private String teacherName;
	
	@ManyToOne
	private Department department;
	
	@ManyToMany(targetEntity = Cohort.class)
	private Set<Cohort> cohortSet;
	
	public Teacher(String salary, String teacherName, Department department) {
		super();
		this.salary = salary;
		this.teacherName = teacherName;
		this.department = department;
	}
	
	public Teacher(String salary, String teacherName, Set<Cohort> cohortSet) {
		super();
		this.salary = salary;
		this.teacherName = teacherName;
		this.cohortSet = cohortSet;
	}
	
	public Set<Cohort> getCohortSet() {
		return cohortSet;
	}

	public void setCohortSet(Set<Cohort> cohortSet) {
		this.cohortSet = cohortSet;
	}

	public int getTit() {
		return tit;
	}

	public void setTit(int tit) {
		this.tit = tit;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	
}
