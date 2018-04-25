package dev.restservices.runners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import dev.restservices.services.CommentsDataService;
import dev.restservices.services.MoviesDataService;

@Component
public class AppRunner implements CommandLineRunner {

	
	private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);
	
    @Autowired
	MoviesDataService moviesDataService;
    
    @Autowired
	CommentsDataService commentsDataService;
	
	public AppRunner(MoviesDataService moviesDataService, CommentsDataService commentsDataService) {
        this.moviesDataService = moviesDataService;
        this.commentsDataService = commentsDataService;
    }
	
	@Override
	public void run(String... args) throws Exception {
		
		moviesDataService.clearMoviesCache();
		
		   for(int i = 0; i < 15 ;i++){
			   logger.info(".... Fetching movies");
				  logger.info("Geting movies "+(i+1) +" time..... -> "+ moviesDataService.getMoviesList());
		   }
		 
		   
		
	}

}
