package dev.restservices.moviesrestapp.contolllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import dev.restservices.controllers.MoviesController;
import dev.restservices.models.Movie;
import dev.restservices.services.MoviesDataService;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class MoviesControllerTests {

	@Mock
	private MoviesDataService moviesDataService;
 
	@InjectMocks
	private MoviesController moviesController;
	 
	private static List<Movie> moviesList;

	@BeforeClass
	public static void setup() {
		Movie movie1 = new Movie(1, "Movie number 1", "Description of movie number 1");
		Movie movie2 = new Movie(2, "Movie number 2", "Description of movie number 2");
		moviesList = new ArrayList<>();
		moviesList.add(movie1);
		moviesList.add(movie2);
	}
	
	@Test
	public void TestGetListOfMovies() throws Exception{
		
		when(moviesDataService.getMoviesList()).thenReturn(moviesList);
	 
		assertEquals(moviesList,moviesController.getMovies());
		
	}
}
