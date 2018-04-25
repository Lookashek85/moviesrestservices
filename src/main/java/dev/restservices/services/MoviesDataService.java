package dev.restservices.services;

import java.util.List;

import dev.restservices.models.Movie;

public interface MoviesDataService {
	
	public List<Movie> getMoviesList();
	public void addMovie(Movie movie);
	public void clearMoviesCache();
}
