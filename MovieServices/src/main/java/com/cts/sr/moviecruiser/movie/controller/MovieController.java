package com.cts.sr.moviecruiser.movie.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cts.sr.moviecruiser.movie.data.MovieDTO;
import com.cts.sr.moviecruiser.movie.model.Movie;
import com.cts.sr.moviecruiser.movie.service.MovieService;
import com.cts.sr.moviecruiser.movie.utils.AppLogger;
import com.cts.sr.moviecruiser.movie.utils.Constants;

import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(path = "/movie")
@CrossOrigin(origins = "*")
@Api(tags={"Movie Controller"})
public class MovieController implements Constants {
	
	@Autowired
	private MovieService movieService;
	
	
	@PostMapping
	@ApiOperation(value="Save or Update Movie",notes="This operation creates a new Movie or updates an existing Movie")
	public @ResponseBody ResponseEntity<?> saveMovie(@RequestBody MovieDTO movie,final ServletRequest req,
			final ServletResponse res) {
		final Claims claims = (Claims) req.getAttribute(JWT_CLAIMS);
		String userId = claims.getSubject();
		try {
			Movie movieModel = new Movie();
			movieModel.setUserId(userId);
			movieModel.copy(movie);
			movieService.saveMovie(movieModel);
			
		} catch (Exception e) {
			AppLogger.error("Exception occurred in saveMovie", e);
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
		return new ResponseEntity<MovieDTO>(movie, HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/{id:[0-9]+}")
	@ApiOperation(value="Delete Movie",notes="This operation deletes an already existing Movie")
	public @ResponseBody ResponseEntity<?> deleteMovie(@PathVariable("id") int id) {
		try {
			movieService.deleteMovie(id);
		} catch (Exception e) {
			AppLogger.error("Exception occurred in deleteMovie", e);
			return new ResponseEntity<Integer>(-1, HttpStatus.OK);
		}
		return new ResponseEntity<Integer>(0, HttpStatus.OK);
	}
	
	@GetMapping(path = "/{id:[0-9]+}")
	@ApiOperation(value="Get Movie By ID",notes="This operation retrieves an already existing Movie by ID")
	public @ResponseBody ResponseEntity<?> getMovieById(@PathVariable("id") int id) {
		MovieDTO movie = new MovieDTO();
		try {
			Movie movieModel = movieService.getMovieById(id);
			movie.copy(movieModel);
		} catch (Exception e) {
			AppLogger.error("Exception occurred in getMovieById", e);
			return new ResponseEntity<Integer>(-1, HttpStatus.OK);
		}
		return new ResponseEntity<MovieDTO>(movie, HttpStatus.OK);
	}
	
	@GetMapping(path = "/all")
	@ApiOperation(value="Get All Movies",notes="This operation retrieves all existing Movies")
	public @ResponseBody ResponseEntity<?> getAllMovies(final HttpServletRequest req,
			final HttpServletResponse res) throws IOException {
		List<MovieDTO> movies = new ArrayList<MovieDTO>();
		final Claims claims = (Claims) req.getAttribute(JWT_CLAIMS);
		String userId = claims.getSubject();
		try {
			List<Movie> movieModels = movieService.getAllMovies(userId);
			movieModels.forEach(movie -> {
				MovieDTO movieDTO = new MovieDTO();
				movieDTO.copy(movie);
				movies.add(movieDTO);
			});
		} catch(Exception e) {
			AppLogger.error("Exception occurred in getAllMovies", e);
			return new ResponseEntity<List<MovieDTO>>(movies, HttpStatus.OK);
		}
		return new ResponseEntity<List<MovieDTO>>(movies, HttpStatus.OK);
	}
}