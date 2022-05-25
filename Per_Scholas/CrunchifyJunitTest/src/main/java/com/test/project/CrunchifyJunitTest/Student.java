package com.test.project.CrunchifyJunitTest;


public class Student {

	public Student() { }
	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getEmail() {
		return Email;
	}


	public void setEmail(String email) {
		Email = email;
	}


	public String getfName() {
		return fName;
	}


	public void setfName(String fName) {
		this.fName = fName;
	}


	public String getlName() {
		return lName;
	}


	public void setlName(String lName) {
		this.lName = lName;
	}


	private long id;
	private String Email;
	private String fName;
	private String lName;
	
	   @Override
		public boolean equals(Object obj) {
			if (obj instanceof Student) {
				Student other = (Student) obj;
				boolean sameId = (this.id == other.getId());
				boolean sameEmail = (this.Email.equals(other.getEmail()));
				boolean sameFName = (this.fName.equals(other.getfName()));
				boolean sameLName = (this.lName.equals(other.getlName()));
				if (sameId && sameEmail && sameFName && sameLName) {
					return true;
				}
			}
			return false;
		}



}