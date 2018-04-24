package dev.restservices.models;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class Movie {
		
	@NotNull
	@Min(value=1)
	private Long id;
	
	@Size(min=3, message="Title must have min 3 characters!")
	private String title;
	
	@Size(min=3, message="Description must have min 3 characters!"  )
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
