package jpa.service;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import jpa.dao.CourseDAO;
import jpa.entitymodels.Course;

public class CourseService implements CourseDAO {
	
	SessionFactory f = new Configuration().configure().buildSessionFactory();
	Session s = f.openSession();
	Transaction t = s.beginTransaction();
	
	public List<Course> getAllCourses() {
		
		Query query = s.createQuery("FROM Course");
		List<Course> courses = query.getResultList();
		return courses;
	}
}