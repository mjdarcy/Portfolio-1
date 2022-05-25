package com.test.controller;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.test.model.User;

public class UserController {

	SessionFactory f = new Configuration().configure().buildSessionFactory();
	Session s = f.openSession();
	Transaction t = s.beginTransaction();
	int testId = 1;
	
	public void CreateUserTable()
	{
		f = new Configuration().configure().buildSessionFactory();
		s = f.openSession();
		t = s.beginTransaction();
		User user = new User();
		
		t.commit();
		System.out.println("Created user table.");
		f.close();
		s.close();
	}
	
	public void CreateUser()
	{
		f = new Configuration().configure().buildSessionFactory();
		s = f.openSession();
		
		t = s.beginTransaction();
		User user = new User("Full name", "Email", "Password", 30, 80000.54, "City");

		s.save(user);
		t.commit();
		System.out.println("User saved.");
		f.close();
		s.close();
	}
	
	public void findUser(int id)
	{
		f = new Configuration().configure().buildSessionFactory();
		s = f.openSession();
		t = s.beginTransaction();
		
		User user = s.load(User.class, id);
		System.out.println("User name: " + user.getFullname());
		t.commit();
		f.close();
		s.close();
	}
	
	public void updateUser(int id)
	{
		f = new Configuration().configure().buildSessionFactory();
		s = f.openSession();
		t = s.beginTransaction();
		
		User u = new User();
		u.setId(id);
		u.setEmail("ajchandler39@gmail.com");
		u.setFullname("alijah chandler");
		u.setPassword("password");
		u.setSalary(80000);
		s.merge(u);
		s.getTransaction().commit();
		s.close();
	}
	
	public void deleteUser(int id)
	{
		f = new Configuration().configure().buildSessionFactory();
		s = f.openSession();
		t = s.beginTransaction();
		
		User u = new User();
		u.setId(id);
		s.delete(u);
		t.commit();
		f.close();
		s.close();
	}
}
