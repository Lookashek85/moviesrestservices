package dev.restservices.moviesrestapp.contolllers;

import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.restservices.models.Comment;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class CommentsControllerTests {
	
	
	@Autowired
	private MockMvc mock;
 
	@Test
	 public void whenPostComment_as_User_thenReturnStatusCreatedAndLocation() throws Exception {
		
		Comment comment  = new Comment(100, 2, "Jerry", "Was ok");
		String movieId = String.valueOf(comment.getMovieId());
		mock.perform(
	            post("/movies/"+movieId+"/comments")
	            .with(httpBasic("user", "userpass"))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .content(asJsonString( comment )))
	            .andExpect(status().isCreated());
    	  
	    }
	
	@Test
	public void whenPostMovies_as_Admin_thenReturnUnauthorized() throws Exception {
		
		Comment comment  = new Comment(100, 2, "Jerry", "Was ok");
		String movieId = String.valueOf(comment.getMovieId());
 
	    mock.perform(
	    		post("/movies/"+movieId+"/comments")
	            .with(httpBasic("admin", "adminpass"))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .content(asJsonString( comment )))
	            .andExpect(status().isForbidden())
	            .andExpect(header().string("location", isEmptyOrNullString()));   
	}
	

	public static String asJsonString(final Object obj) {
		
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
