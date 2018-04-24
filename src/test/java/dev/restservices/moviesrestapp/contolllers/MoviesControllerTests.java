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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import dev.restservices.controllers.MoviesController;
import dev.restservices.models.Movie;
import dev.restservices.services.MoviesDataService;

@RunWith(SpringJUnit4ClassRunner.class)
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class})
//@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MoviesControllerTests {

	private static List<Movie> moviesList;

	@Mock
	private MoviesDataService moviesDataService;

	@InjectMocks
	private MoviesController moviesController;

	@Autowired
	private MockMvc mockMvc;

	@BeforeClass
	public static void setup() {
		Movie movie1 = new Movie(1, "Movie number 1", "Description of movie number 1");
		Movie movie2 = new Movie(2, "Movie number 2", "Description of movie number 2");
		moviesList = new ArrayList<>();
		moviesList.add(movie1);
		moviesList.add(movie2);
	}
	
 
	@Test
	public void TestGetListOfMovies() throws Exception {

		when(moviesDataService.getMoviesList()).thenReturn(moviesList);

		assertEquals(moviesList, moviesController.getMovies());

	}
	
	@Test //failing for now. 
	public void createStudentCourse() throws Exception {
		
		
	 
		String movieJson =  "{\"id\": \"3\","
				+ "\"title\" : \"Added movie\","
				+ "\"description\" : \"Added movie comment\"}";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.
				 post("/movies")
				.accept(MediaType.APPLICATION_JSON).content(movieJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		assertEquals("http://localhost/movies/3",
				response.getHeader(HttpHeaders.LOCATION));
		
	}
 
}
