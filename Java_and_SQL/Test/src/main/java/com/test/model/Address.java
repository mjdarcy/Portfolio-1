package com.test.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int AddId;
	private String st;
	private String city;
	private String state;
	private int zip;
	
	
	
	public Address() {
		super();
	}

	public Address(String st, String city, String state, int zip) {
		super();
		this.st = st;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
	
	public int getAddId() {
		return AddId;
	}
	public void setAddId(int addId) {
		AddId = addId;
	}
	public String getSt() {
		return st;
	}
	public void setSt(String st) {
		this.st = st;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getZip() {
		return zip;
	}
	public void setZip(int zip) {
		this.zip = zip;
	}
	
}
