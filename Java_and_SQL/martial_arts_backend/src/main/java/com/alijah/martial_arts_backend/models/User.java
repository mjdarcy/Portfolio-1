package com.alijah.martial_arts_backend.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class User {
	
	@Id
	String username;
	String password;
	String firstName;
	String lastName;
	String email;
	
	@ManyToMany(cascade=CascadeType.ALL)
	private Set<Technique> favorites;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="creator")
	private Set<Technique> techs;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="username")
	private Set<Message> sentMessages;
	
	@OneToMany
	@JoinColumn(name="recipient")
	private Set<Message> receivedMessages;

	public User() {}

	public User(String username, String password, String firstName, String lastName, String email,
			Set<Technique> favorites, Set<Technique> techs, Set<Message> sentMessages, Set<Message> receivedMessages) {
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

	public Set<Technique> getFavorites() {
		return favorites;
	}

	public void setFavorites(Set<Technique> favorites) {
		this.favorites = favorites;
	}

	public Set<Technique> getTechs() {
		return techs;
	}

	public void setTechs(Set<Technique> techs) {
		this.techs = techs;
	}

	public Set<Message> getSentMessages() {
		return sentMessages;
	}

	public void setSentMessages(Set<Message> sentMessages) {
		this.sentMessages = sentMessages;
	}

	public Set<Message> getReceivedMessages() {
		return receivedMessages;
	}

	public void setReceivedMessages(Set<Message> receivedMessages) {
		this.receivedMessages = receivedMessages;
	}
	
	
}