package dev.restservices.exceptions;

public class CommentsServiceException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private String message;

	public CommentsServiceException(String message) {
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
		return "CommentsServiceException [message=" + message + "]";
	}

}
