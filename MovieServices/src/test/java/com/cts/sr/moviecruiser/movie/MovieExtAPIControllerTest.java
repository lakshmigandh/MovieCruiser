package com.cts.sr.moviecruiser.movie;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.cts.sr.moviecruiser.movie.controller.MovieExtAPIController;

@RunWith(SpringRunner.class)
@WebMvcTest(MovieExtAPIController.class)
@PropertySource("classpath:movie.ext.api.properties")
public class MovieExtAPIControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testSearchEndPoint() throws Exception {
		mockMvc.perform(get("/movie/api/search/Shawshank")).andExpect(status().isOk());
	}
	
	@Test
	public void testUpcoming() throws Exception {
		mockMvc.perform(get("/movie/api/upcoming/")).andExpect(status().isOk());
	}
	
	@Test
	public void testTrending() throws Exception {
		mockMvc.perform(get("/movie/api/trending/")).andExpect(status().isOk());
	}
	
	@Test
	public void testRecommended() throws Exception {
		mockMvc.perform(get("/movie/api/recommended/286")).andExpect(status().isOk());
	}
	
	
	
	
	
}