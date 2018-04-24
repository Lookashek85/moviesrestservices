package dev.restservices.exceptions;

import java.lang.reflect.Method;
import java.util.Date;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class MoviesRestServiceExceptionHandler extends ResponseEntityExceptionHandler
		implements AsyncUncaughtExceptionHandler {
 
	// Handles exceptions thrown by MoviesService
	@ExceptionHandler(MoviesServiceException.class)
	protected ResponseEntity<ServiceExceptionDetails> handleMoviesServiceExceptions(MoviesServiceException ex,
			WebRequest request) {

		ServiceExceptionDetails errorDetails = new ServiceExceptionDetails(new Date(), ex.getMessage(),request.getDescription(false));
		
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}


	// Handles exceptions thrown by CommentsService
	@ExceptionHandler(CommentsServiceException.class)
	protected ResponseEntity<ServiceExceptionDetails> handleCommentsServiceExceptions(CommentsServiceException ex,
			WebRequest request) {
 
		ServiceExceptionDetails errorDetails = new ServiceExceptionDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		 
		    ServiceExceptionDetails errorDetails = new ServiceExceptionDetails(new Date(), "Validation failed , see details..." , ex.getBindingResult().toString());
		  
		    return ResponseEntity.badRequest()				  
				  .contentType(MediaType.APPLICATION_JSON)
				  .body(errorDetails);	 
	}


	// Handles async methods exceptions....
	@Override
	public void handleUncaughtException(Throwable arg0, Method arg1, Object... arg2) {
		 

	}

}
