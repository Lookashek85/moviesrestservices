package dev.restservices.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import dev.restservices.exceptions.CommentsServiceException;
import dev.restservices.models.Comment;
import dev.restservices.services.CommentsDataService;

@RestController
public class CommentsController {

	@Autowired
	CommentsDataService commentsDataService;
	
	@PostMapping(path="/movies/{movieId}/comments")
	public ResponseEntity<Comment> addComment(@Valid @RequestBody Comment comment, @PathVariable(value="movieId", required=true) long movieId) throws CommentsServiceException{
		//for testing exception handling, we can try to add a negative id.
		long commentid = comment.getId();
		if(movieId <=0 ){
			throw new CommentsServiceException("INVALID MOVIE ID!");
		}
   
		URI newLocation = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{commentid}")
			.buildAndExpand(commentid).toUri();
		
		return ResponseEntity.created(newLocation).build();
		
	}
}
