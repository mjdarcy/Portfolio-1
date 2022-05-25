package com.jpa.test;

import java.util.ArrayList;

import org.junit.Test;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import jpa.service.StudentService;
import junit.framework.Assert;

public class SMSRunnerTest {

	@Test
	public void testGetStudentByEmail()
	{
		StudentService s = new StudentService();
		Student expectedStudent = new Student("aiannitti7@is.gd", "Alexandra \r\nIannitti", "TWP4hf5j", new ArrayList<Course>());
		Student foundStudent = s.getStudentByEmail("aiannitti7@is.gd");
		Assert.assertEquals(expectedStudent.getName(), foundStudent.getName());
	}
}
