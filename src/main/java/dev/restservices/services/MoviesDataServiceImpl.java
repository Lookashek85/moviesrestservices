package dev.restservices.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import dev.restservices.models.Comment;
import dev.restservices.models.Movie;

@Service
public class MoviesDataServiceImpl implements MoviesDataService {
	
	private static List<Movie> movies = new ArrayList<>();
	//Loading some dummy data , typically data would come from some DAO
	static{
		 
	    Movie movie1 = new Movie(001,"Star wars", "Luke Skywalker fighting Lord Vader...");	
	    Movie movie2 = new Movie(002,"Lion King","Great cartoon movie for kids");
	    
	    Comment comment1m1 = new Comment(movie1.getId()*10,movie1.getId(),"Paul", "Best part so far");
	    Comment comment2m1 = new Comment(movie1.getId()*10,movie1.getId(),"Mick", "Worst SW movie I've seen");
	    
	    Comment comment1m2 = new Comment(movie2.getId()*10,movie2.getId(),"Anna", "My kids loved it!");
	    Comment comment2m2 = new Comment(movie2.getId()*10,movie2.getId(),"Peter", "I couldn't stop crying!");
	    
	    movie1.addComment(comment1m1);
	    movie1.addComment(comment2m1);
	    
	    movie2.addComment(comment1m2);
	    movie2.addComment(comment2m2);
	    
	    movies.add(movie1);
		movies.add(movie2);
	}
 
	@Override
	public List<Movie> getMoviesList() {
		return movies;
	}

	@Override
	public void addMovie(Movie movie) {
		 
		movies.add(movie);
	}
	

}
