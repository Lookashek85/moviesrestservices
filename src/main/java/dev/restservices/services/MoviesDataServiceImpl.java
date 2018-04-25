package dev.restservices.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import dev.restservices.models.Comment;
import dev.restservices.models.Movie;

@Service
@CacheConfig(cacheNames="movies")
public class MoviesDataServiceImpl implements MoviesDataService {
	
	private static Logger logger = LoggerFactory.getLogger(MoviesDataServiceImpl.class);
	
	@Autowired
	CommentsDataService commentsDataService;
	
	private static List<Movie> movies = new ArrayList<>();
	//Loading some dummy data , typically data would come from some DAO
	static{
		 
	    Movie movie1 = new Movie(1,"Star wars", "Luke Skywalker fighting Lord Vader...");	
	    Movie movie2 = new Movie(2,"Lion King","Great cartoon movie for kids");
	    movies.add(movie1);
		movies.add(movie2);
	}
 
	@Override
	@Cacheable(cacheNames = "movies"/*,key = "#root.methodName"*/)
	public List<Movie> getMoviesList() {
		
		logger.info("Executing: " + this.getClass().getSimpleName() + ".getMoviesList()");
		
		simulateSlowGetMovies();
		
		for(Movie movie : movies){
			//List<Comment> comments = commentsDataService.getCommentsForTheMovie(movie.getId()); 
			movie.setComments(commentsDataService.getCommentsForTheMovie(movie.getId()));
		}
		
		return movies;
	}
	
 
	@Override
	public void addMovie(Movie movie) {
		 
		movies.add(movie);
	}


	@Override
	@CacheEvict(allEntries = true)
	public void clearMoviesCache() {
		// TODO Auto-generated method stub
		
	}
	
	 // Only for testing....
    private void simulateSlowGetMovies() {
        try {
            long time = 2000L;
         
            logger.info("Executing long query...."); 
            Thread.sleep(time);
         
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

   


}
