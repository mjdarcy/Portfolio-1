package com.martialartsapp.technique;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Technique {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String creator;
	private String name;
	private String type;
	private String description;
	@Lob
	private byte[] video;

	public Technique()
	{
		this.name = "";
		this.type = "";
		this.description = "";
		this.creator = "";
		this.video = new byte[]{};
	}
	
	public Technique(String creator, String name, String type, String description, byte[] video) 
	{
		this();
		this.creator = creator;
		this.name = name;
		this.type = type;
		this.description = description;
		this.video = video;
	}

	public Technique(Integer id, String creator, String name, String type, String description, byte[] video) 
	{
		this();
		this.id = id;
		this.creator = creator;
		this.name = name;
		this.type = type;
		this.description = description;
		this.video = video;
	}

	public Integer getId() { return id; }
	public String getCreator() { return creator; }
	public String getName() { return name; }
	public String getType() { return type; }
	public byte[] getVideo() { return this.video; }
	public String getDescription() { return description; }

	public void setId(Integer id) { this.id = id; }
	public void setName(String name) { this.name = name; }
	public void setType(String type) { this.type = type; }
	public void setVideo(byte[] video) { this.video = video; }
	public void setDescription(String description) { this.description = description; }
}
