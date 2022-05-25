package jpa.entitymodels;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Course {

	@Id
	@Column(name="id", nullable=false)
	private int cId;
	@Column(name="name", nullable=false, length=50)
	private String cName;
	@Column(name="instructor", nullable=false, length=50)
	private String cInstructor;
	
	public Course() 
	{ 
		this.cId = 0;
		this.cName = "";
		this.cInstructor = "";
	}
	
	public Course(int id, String name, String instructor) {
		super();
		this.cId = id;
		this.cName = name;
		this.cInstructor = instructor;
	}

	public int getId() { return cId; }
	public void setId(int id) { this.cId = id; }

	public String getName() { return cName; }
	public void setName(String name) { this.cName = name; }

	public String getInstructor() { return cInstructor; }
	public void setInstructor(String instructor) { this.cInstructor = instructor; }
}