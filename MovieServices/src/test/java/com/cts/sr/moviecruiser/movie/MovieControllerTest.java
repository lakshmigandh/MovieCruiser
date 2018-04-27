package com.cts.sr.moviecruiser.movie;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cts.sr.moviecruiser.movie.controller.MovieController;
import com.cts.sr.moviecruiser.movie.model.Movie;
import com.cts.sr.moviecruiser.movie.service.MovieService;
import com.cts.sr.moviecruiser.movie.utils.Constants;
import com.cts.sr.moviecruiser.movie.utils.MovieUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RunWith(SpringRunner.class)
@WebMvcTest(MovieController.class)
public class MovieControllerTest implements Constants{
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private MovieService dao;
	
	@InjectMocks
	private MovieController movieController;
	
	private Movie movie;
	
	static List<Movie> movieList;
	
	static String userId;
	
	@Before
	public void setUpMock() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
		userId = "LakshmiGandh";
		movieList = new ArrayList<Movie>();
		movie = new Movie(1L,userId, "Test Movie1", "234324234.jpg", "01-01-2018", 9.0, 100L);
		movieList.add(movie);
		movie = new Movie(2L,userId, "Test Movie2", "12321213123.jpg", "03-01-2018", 8.7, 108L);
		movieList.add(movie);
	}
	
	@Test
	public void testGetMovie() throws Exception {
		when(dao.getMovieById(1)).thenReturn(movieList.get(0));
		mockMvc.perform(get("/movie/{id}", 1)).andExpect(status().isOk());
		verify(dao, times(1)).getMovieById(1);
		verifyNoMoreInteractions(dao);
	}
	
	
	
	@Test
	public void testGetAllMovie() throws Exception {
		String jwtToken = Jwts.builder().setSubject(userId).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, JWT_SECRET_KEY).compact();
		Map<String, String> map = new HashMap<>();
		map.put(JWT_TOKEN, jwtToken);
		Claims claims = Jwts.parser().setSigningKey(JWT_SECRET_KEY).parseClaimsJws(jwtToken).getBody();
		when(dao.getAllMovies(userId)).thenReturn(movieList);
		mockMvc.perform(get("/movie/all").requestAttr(JWT_CLAIMS, claims)).andExpect(status().isOk());
		verify(dao, times(1)).getAllMovies(userId);
    }
	
	@Test
	public void testSaveMovie() throws Exception {

		String jwtToken = Jwts.builder().setSubject(userId).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, JWT_SECRET_KEY).compact();
		Map<String, String> map = new HashMap<>();
		map.put(JWT_TOKEN, jwtToken);
		Claims claims = Jwts.parser().setSigningKey(JWT_SECRET_KEY).parseClaimsJws(jwtToken).getBody();
		when(dao.saveMovie(movie)).thenReturn(true);
		mockMvc.perform(post("/movie").requestAttr(JWT_CLAIMS, claims).contentType(MediaType.APPLICATION_JSON).content(MovieUtils.getObjectAsString((movie)))).andExpect(status().isCreated());
		verify(dao, times(1)).saveMovie(Mockito.any(Movie.class));
	}
	
	
	@Test
	public void testDeleteMovie() throws Exception {
		when(dao.deleteMovie(1)).thenReturn(true);
		mockMvc.perform(delete("/movie/{id}", 1)).andExpect(status().isOk());
		verify(dao, times(1)).deleteMovie(1);
	}
	
	
	
}