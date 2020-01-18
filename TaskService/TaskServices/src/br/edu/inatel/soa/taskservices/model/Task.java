package br.edu.inatel.soa.taskservices.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Task {
	private Long id;
	private Boolean done = false;
	private String description;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Boolean getDone() {
		return done;
	}
	public void setDone(Boolean done) {
		this.done = done;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}	
	
}
