package dev.restservices.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import dev.restservices.exceptions.MoviesServiceException;
import dev.restservices.models.Movie;
import dev.restservices.services.MoviesDataService;

@RestController
public class MoviesController {

	private static Logger logger = LoggerFactory.getLogger(MoviesController.class);
	
	
	@Autowired   //Simulating DAO 
	MoviesDataService moviesDataService;
	

    @GetMapping(path="/movies")
	public ResponseEntity<List<Movie>> getMovies(){
    	
		List<Movie> movies = moviesDataService.getMoviesList();
		   
		   if(movies == null ){
			   throw new MoviesServiceException("Couldn't find the list of movies");
		   }
		   else if(movies.isEmpty()){   
			   throw new MoviesServiceException("List of movies exist but it's empty!");   
		   }
		   return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);
	}
	
 
	@PostMapping(path="/movies")
	public ResponseEntity<Void> addMovie(@Valid @RequestBody Movie movie){
 
		Long movieId = movie.getId();
		 
		URI newLocation = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(movieId).toUri();
		
		logger.info("Adding new resource at : ", newLocation.toString());
		
		return ResponseEntity.created(newLocation).contentType(MediaType.APPLICATION_JSON).build();
		// No communication with the service... 	
		//moviesService.addMovie(newMovie);	
	}
}
