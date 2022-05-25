package com.martialartsapp.user;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.martialartsapp.technique.Technique;

@Entity
public class User {
	
	@Id
	String username;
	String password;
	String firstName;
	String lastName;
	String email;
	
	@ManyToMany(cascade=CascadeType.ALL)
	List<Technique> favorites;
	
	@OneToMany(cascade=CascadeType.ALL)
	List<Technique> userTechs;
	
	@OneToMany(cascade=CascadeType.ALL)
	List<Technique> sentMessages;
	
	@OneToMany//(cascade=CascadeType.ALL)
	List<Technique> receivedMessages;
	
	public User()
	{
		this.username = "";
		this.password = "";
		this.firstName = "";
		this.lastName = "";
		this.email = "";
	}
	
	public User(String username, String password, String firstName, String lastName, String email)
	{
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	public String getUsername() { return username; }
	public void setUsername(String username) { this.username = username; }
	
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }

	public String getFirstName() { return firstName; }
	public void setFirstName(String firstName) { this.firstName = firstName; }

	public String getLastName() { return lastName; }
	public void setLastName(String lastName) { this.lastName = lastName; }

	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }

	public List<Technique> getFavorites() { return favorites; }
	public void setFavorites(List<Technique> favorites) { this.favorites = favorites; }

	public List<Technique> getUserTechs() { return userTechs; }
	public void setUserTechs(List<Technique> userTechs) { this.userTechs = userTechs; }
}