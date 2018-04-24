package dev.restservices.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.restservices.models.Movie;
import dev.restservices.services.MoviesDataService;

@RestController
public class MoviesController {

	@Autowired   //Simulating DAO 
	MoviesDataService moviesService;
	
	@GetMapping(path="/movies")
	public List<Movie> getMovies(){
		
		//List<Movie> moviesList = ;
		
		return moviesService.getMoviesList();
	}
	
	
	@GetMapping(path="/hello")
	public String returnMe(){
		return "HI LUKAS!";
	}
}
