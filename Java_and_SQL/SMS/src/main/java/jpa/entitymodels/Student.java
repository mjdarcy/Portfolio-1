package jpa.entitymodels;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Student {
	
	@Id
	@Column(name="email", nullable=false, length=50)
	private String sEmail;
	@Column(name="name", nullable=false, length=50)
	private String sName;
	@Column(name="password", nullable=false, length=50)
	private String sPassword;
	
	@ManyToMany(cascade=CascadeType.ALL)
	List<Course> sCourses;
	
	public Student()
	{
		this.sEmail = "";
		this.sName = "";
		this.sPassword = "";
		sCourses = new ArrayList<Course>();
	}
	
	public Student(String email, String name, String password, List<Course> sCourses)
	{
		this();
		this.sEmail = email;
		this.sName = name;
		this.sPassword = password;
		this.sCourses = sCourses;
	}

	public String getEmail() { return sEmail; }
	public void setEmail(String email) { this.sEmail = email; }

	public String getName() { return sName; }
	public void setName(String name) { this.sName = name; }

	public String getPassword() { return sPassword; }
	public void setPassword(String password) { this.sPassword = password; }

	public List<Course> getsCourses() { return sCourses; }
	public void setsCourses(List<Course> sCourses) { this.sCourses = sCourses; }
	
	
}