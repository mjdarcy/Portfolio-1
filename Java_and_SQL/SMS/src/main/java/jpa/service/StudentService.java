package jpa.service;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import jpa.dao.StudentDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;

public class StudentService implements StudentDAO {

	SessionFactory f = new Configuration().configure().buildSessionFactory();
	public Session s = f.openSession();
	Transaction t = s.beginTransaction();
	
	public List<Student> getAllStudents() {
		
		Query query = s.createQuery("FROM Student");
		List<Student> result = query.getResultList();
		
		return result;
	}

	public Student getStudentByEmail(String sEmail) {
		
		Query query = s.createQuery("FROM Student where id = :id").setParameter("id", sEmail);
		List<Student> result = query.getResultList();
		
		return result.get(0);
	}

	public boolean validateStudent(String sEmail, String sPassword) 
	{
		
		Query query = s.createQuery("FROM Student WHERE email = :email AND password = :password")
			.setParameter("email", sEmail)
			.setParameter("password", sPassword);
		
		List<Student> result = query.getResultList();
		
		return result.size() > 0;
	}

	public void registerStudentToCourse(String sEmail, int cId) {
		
		Student st = this.getStudentByEmail(sEmail);
		for(Course c : st.getsCourses()) if(c.getId() == cId) return;
		
		Query query = s.createQuery("FROM Course WHERE id = :id")
				.setParameter("id", cId);
		List<Course> result = query.getResultList();
		st.getsCourses().add(result.get(0));
		s.update(st);
		t.commit();
		t = s.beginTransaction();
	}

	public List<Course> getStudentCourses(String sEmail) {
		
		Query query = s.createQuery("FROM Student WHERE email = :email")
				.setParameter("email", sEmail);
		List<Student> result = query.getResultList();
		return result.get(0).getsCourses();
	}

}
