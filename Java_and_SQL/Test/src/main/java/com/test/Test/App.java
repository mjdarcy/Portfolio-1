package com.test.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.test.model.Cohort;
import com.test.model.Teacher;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
    	Driver d = new Driver();
    	d.start();
    	/*
    	int id = 3;
        UserController uc = new UserController();
        uc.CreateUserTable();
        uc.CreateUser();
        uc.findUser(id);
        uc.updateUser(id);
        uc.findUser(id);
        uc.deleteUser(id);
    	
    	SessionFactory f = new Configuration().configure().buildSessionFactory();
    	Session session = f.openSession();
    	
    	Transaction t = session.beginTransaction();
    	Address a1 = new Address("NYC", "NY", "27th st", 11103);
    	Address a2 = new Address("NYC", "NY", "27th st", 11103);
    	
    	Person p1 = new Person("Albert Einstein", "email@gmail.com", 25, a1);
    	Person p2 = new Person("Albert Einsteins brother", "email2@gmail.com", 30, a2);
    	
    	session.save(a1);
    	session.save(a2);
    	session.save(p1);
    	session.save(p2);
    	t.commit();
    	Department dep = new Department("IT");
    	Department dep2 = new Department("IT");
    	//String salary, String teacherName, Department department
    	Teacher t1 = new Teacher("50000", "Joe", dep);
    	Teacher t2 = new Teacher("50000", "Joe2", dep);
    	
    	Teacher t3 = new Teacher("50000", "Joe3", dep2);
    	Teacher t4 = new Teacher("50000", "Joe4", dep2);
    	
    	session.save(dep);
    	session.save(dep2);
    	session.save(t1);
    	session.save(t2);
    	session.save(t3);
    	session.save(t4);
    	t.commit();
    	
    	Cohort class1 = new Cohort("Java dev", "14 weeks");
    	Cohort class2 = new Cohort("python", "7 weeks");
    	Cohort class3 = new Cohort("fullstack", "32 weeks");
    	
    	session.save(class1);
    	session.save(class2);
    	session.save(class3);
    	
    	Set<Cohort> classSet1 = new HashSet<Cohort>();
    	classSet1.add(class1);
    	classSet1.add(class2);
    	classSet1.add(class3);
    	
    	Set<Cohort> classSet2 = new HashSet<Cohort>();
    	classSet2.add(class1);
    	classSet2.add(class3);
    	classSet2.add(class2);
    	
    	Teacher t1 = new Teacher("200", "Jamie", classSet1);
    	Teacher t2 = new Teacher("300", "Cersei", classSet2);
    	
    	session.save(t1);
    	session.save(t2);
    	t.commit();
    	*/
    }
}

class Driver {

	  void start() throws Exception {

	    /* You can ignore this block and assuming the connection was successful*/
	    //Class.forName("org.mariadb.jdbc.Driver");
	    Connection conn = DriverManager.getConnection(
	      "jdbc:mysql://localhost:3306/",
	      "root",
	      "LyokoFroYo0!");
	    /*------------------------*/

	    Statement st = conn.createStatement();
	    ResultSet rs = st.executeQuery("show databases");

	    //while (rs.next() == true) {
	    System.out.println(rs.getString(0));
	    //}
	  }
	}
