package com.martialartsapp.user;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.martialartsapp.message.Message;
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
	private List<Technique> favorites;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="creator")
	private List<Technique> techs;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="username")
	private List<Message> sentMessages;
	
	@OneToMany
	@JoinColumn(name="recipient")
	private List<Message> receivedMessages;

	public User() {}

	public User(String username, String password, String firstName, String lastName, String email,
			List<Technique> favorites, List<Technique> techs, List<Message> sentMessages, List<Message> receivedMessages) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.favorites = favorites;
		this.techs = techs;
		this.sentMessages = sentMessages;
		this.receivedMessages = receivedMessages;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Technique> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<Technique> favorites) {
		this.favorites = favorites;
	}

	public List<Technique> getTechs() {
		return techs;
	}

	public void setTechs(List<Technique> techs) {
		this.techs = techs;
	}

	public List<Message> getSentMessages() {
		return sentMessages;
	}

	public void setSentMessages(List<Message> sentMessages) {
		this.sentMessages = sentMessages;
	}

	public List<Message> getReceivedMessages() {
		return receivedMessages;
	}

	public void setReceivedMessages(List<Message> receivedMessages) {
		this.receivedMessages = receivedMessages;
	}
	
	
}