package com.cts.sr.moviecruiser;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.cts.sr.moviecruiser.controller.MovieController;
import com.cts.sr.moviecruiser.dao.IMovieDAO;
import com.cts.sr.moviecruiser.model.Movie;
import com.cts.sr.moviecruiser.utils.MovieUtils;

@RunWith(SpringRunner.class)
@WebMvcTest(MovieController.class)
public class MovieControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private IMovieDAO dao;
	
	private Movie movie;
	
	static List<Movie> movieList;
	
	@Before
	public void setUpMock() {
		movieList = new ArrayList<Movie>();
		movie = new Movie(1L, "Test Movie1", "234324234.jpg", "01-01-2018", 9.0, 100L);
		movieList.add(movie);
		movie = new Movie(2L, "Test Movie2", "12321213123.jpg", "03-01-2018", 8.7, 108L);
		movieList.add(movie);
	}
	
	@Test
	public void testGetMovie() throws Exception {
		when(dao.getMovieById(1)).thenReturn(movieList.get(0));
		mockMvc.perform(get("/movie/{id}", 1)).andExpect(status().isOk());
		verify(dao, times(1)).getMovieById(1);
	}
	
	@Test
	public void testGetAllMovie() throws Exception {
		when(dao.getAllMovies()).thenReturn(movieList);
		mockMvc.perform(get("/movie/all")).andExpect(status().isOk());
		verify(dao, times(1)).getAllMovies();
	}
	
	@Test
	public void testSaveMovie() throws Exception {
		when(dao.saveMovie(movie)).thenReturn(true);
		mockMvc.perform(post("/movie").contentType(MediaType.APPLICATION_JSON).content(MovieUtils.getObjectAsString((movie)))).andExpect(status().isCreated());
		verify(dao, times(1)).saveMovie(Mockito.any(Movie.class));
	}
	
	@Test
	public void testDeleteMovie() throws Exception {
		when(dao.deleteMovie(1)).thenReturn(true);
		mockMvc.perform(delete("/movie/{id}", 1)).andExpect(status().isOk());
		verify(dao, times(1)).deleteMovie(1);
	}
	
	
	
}