package org.subbu.restweb.messenger.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;




public class Message {

	private long id;
	private String message;
	private Date created;
	private String author;	
	private List<Link> links = new ArrayList<Link>();
	
	public Message(){
		
	}
	
	public Message(long id, String message, String author) {
		super();
		this.id = id;
		this.message = message;
		this.created = new Date();
		this.author = author;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}
	
	public Link getLinkUrl(String url, String rel){
		Link link = new Link();
		link.setRel(rel);
		link.setUrl(url);
		return link;	
	}
	
	
}
