package dev.restservices.models;

public class Comment {
	
	private long id;
	private long movieId;
	private String username;
	private String message;
	
	protected Comment(){}
	
	public Comment(long id, long movieId, String username, String message) {
		super();
		this.id = id;
		this.movieId = movieId;
		this.username = username;
		this.message = message;
	}
	
	public long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}

	public long getMovieId() {
		return movieId;
	}

	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Comment [movieId=" + movieId + ", username=" + username + ", message=" + message + "]";
	}

}
