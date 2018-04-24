package dev.restservices.moviesrestapp.contolllers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.restservices.models.Movie;
import dev.restservices.services.MoviesDataService;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class MoviesControllerTests {

	private static List<Movie> moviesList;

	@Before
	public void setup() {
		Movie movie1 = new Movie(1, "Movie number 1", "Description of movie number 1");
		Movie movie2 = new Movie(2, "Movie number 2", "Description of movie number 2");
		Movie movie3 = new Movie(3, "Movie number 3", "Description of movie number 3");
		moviesList = new ArrayList<>();
		moviesList.add(movie1);
		moviesList.add(movie2);
		moviesList.add(movie3);
	}
	
	@Autowired
	private MockMvc mock;
	
	@MockBean
	MoviesDataService moviesDataService;
 
	
	@Test
	 public void whenGetMovies_as_User_thenReturnJSON () throws Exception {
			
		 when(moviesDataService.getMoviesList()).thenReturn(moviesList);
			
	     this.mock.perform(get("/movies")
	                .with(httpBasic("user", "userpass")))
	                .andExpect(status().isOk());
	    }
	
	@Test
	public void whenGetMovies_as_Admin_thenReturnJSON () throws Exception{
	 
		 when(moviesDataService.getMoviesList()).thenReturn(moviesList);
		 
		 this.mock.perform(get("/movies")
				 .with(httpBasic("admin", "adminpass"))
			     .contentType("application/json"))
			     .andExpect(status().isOk())
			     .andExpect(jsonPath("$",hasSize(3)))
			     .andExpect(jsonPath("$[0].title").value("Movie number 1")) 
			     .andExpect(jsonPath("$[1].description").value("Description of movie number 2"))
			     .andExpect(jsonPath("$[2].id").value("3"));	 	
	} 
	
	@Test
	public void whenGetMovies_as_Guest_thenReturnForbidden() throws Exception{
	 
		 when(moviesDataService.getMoviesList()).thenReturn(moviesList);
		 
		 this.mock.perform(get("/movies")
				 .with(httpBasic("guest", "guestpass")))
			     .andExpect(status().isUnauthorized());
    	} 
	
	
	@Test
	public void whenPostMovies_as_User_thenReturnUnauthorized() throws Exception {
		
		Movie movie = new Movie(4,"Movie 4","Comment 4");
 
	    mock.perform(
	            post("/movies")
	            .with(httpBasic("user", "userpass"))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .content(asJsonString( movie )))
	            .andExpect(status().isForbidden())
	            .andExpect(header().string("location", isEmptyOrNullString()));   
	}
	

	@Test
	public void whenPostMovies_as_Admin_thenReturnStatusCreatedAndLocation() throws Exception{
	 
		Movie movie = new Movie(4,"Movie 4","Comment 4");
		 
	    mock.perform(
	            post("/movies")
	            .with(httpBasic("admin", "adminpass"))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .content(asJsonString( movie )))
	            .andExpect(status().isCreated())
	            .andExpect(header().string("location", containsString("http://localhost/movies/4"))); 
    	} 
	
	
	public static String asJsonString(final Object obj) {
		
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
 

}
