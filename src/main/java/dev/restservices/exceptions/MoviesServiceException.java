package dev.restservices.exceptions;

public class MoviesServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String message;

	public MoviesServiceException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "MoviesServiceException [message=" + message + "]";
	}

}
