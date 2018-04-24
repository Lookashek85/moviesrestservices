package dev.restservices.moviesrestapp;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import dev.restservices.controllers.MoviesController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MoviesRestAppApplicationTests {

	
	@Autowired
	MoviesController moviesController;
	
	@Test
	public void contextLoads() {
		assertThat(moviesController).isNotNull();
		
	}

}
