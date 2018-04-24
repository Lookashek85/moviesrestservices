package dev.restservices.models;

import java.util.ArrayList;
import java.util.List;


public class Movie {
		
	private Long id;

	private String title;

	private String description;
	
	private List<Comment> comments;
	
	protected Movie(){}

	public Movie(long id, String title, String description, List<Comment> comments) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.comments = comments;
	}
	
	public Movie(long id, String title, String description) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		
		this.comments = comments;
	}
	
	public void addComment(Comment comment){
		if(this.comments == null){
			this.comments = new ArrayList<>();		
		}
		this.comments.add(comment);
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", description=" + description;
	}

	
}
