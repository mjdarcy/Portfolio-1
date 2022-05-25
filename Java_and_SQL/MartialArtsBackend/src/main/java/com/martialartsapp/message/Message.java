package com.martialartsapp.message;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Message {
	
	//I can have page numbers then get a set of pages.
	//i.e. page 1, 1-10, page 2, 10-20
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	String username;
	Date datePosted;
	String contents;
	String recipient;
	//for blog posts, recipient = "blog".
	public Message() {
		
	}
	
	public Message(String username, String contents, String recipient)
	{
		this.username = username;
		this.contents = contents;
		this.recipient = recipient;
	}

	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	
	public String getUsername() {return username;}
	public void setUsername(String username) {this.username = username;}
	
	public Date getDatePosted() {return datePosted;}
	public void setDatePosted(Date datePosted) {this.datePosted = datePosted;}
	
	public String getContents() {return contents;}
	public void setContents(String contents) {this.contents = contents;}
	
	public String getRecipient() {return recipient;}
	public void setRecipient(String recipient) {this.recipient = recipient;}
	
	
}
