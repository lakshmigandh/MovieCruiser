package com.cts.sr.moviecruiser.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cts.sr.moviecruiser.dao.IMovieDAO;
import com.cts.sr.moviecruiser.model.Movie;
import com.cts.sr.moviecruiser.utils.AppLogger;

import data.MovieDTO;

@Controller
@RequestMapping(path = "/movie")
@CrossOrigin(origins = "*")
public class MovieController {
	
	@Autowired
	private IMovieDAO movieDAO;
	
	@PostMapping
	public @ResponseBody ResponseEntity<?> saveMovie(@RequestBody MovieDTO movie) {
		try {
			Movie movieModel = new Movie();
			movieModel.copy(movie);
			movieDAO.saveMovie(movieModel);
			
		} catch (Exception e) {
			AppLogger.error("Exception occurred in saveMovie", e);
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
		return new ResponseEntity<MovieDTO>(movie, HttpStatus.CREATED);
	}
	
	@PutMapping
	public @ResponseBody ResponseEntity<?> updateMovie(@RequestBody MovieDTO movie) {
		try {
			Movie movieModel = movieDAO.getMovieById(movie.getId());
			if(movieModel == null)
				return new ResponseEntity<Integer>(-1, HttpStatus.NOT_FOUND);
			movieModel.copy(movie);
			movieDAO.saveMovie(movieModel);
			
		} catch (Exception e) {
			AppLogger.error("Exception occurred in updateMovie", e);
			return new ResponseEntity<Integer>(-1, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<MovieDTO>(movie, HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/{id:[0-9]+}")
	public @ResponseBody ResponseEntity<?> deleteMovie(@PathVariable("id") int id) {
		try {
			movieDAO.deleteMovie(id);
		} catch (Exception e) {
			AppLogger.error("Exception occurred in deleteMovie", e);
			return new ResponseEntity<Integer>(-1, HttpStatus.OK);
		}
		return new ResponseEntity<Integer>(0, HttpStatus.OK);
	}
	
	@GetMapping(path = "/{id:[0-9]+}")
	public @ResponseBody ResponseEntity<?> getMovieById(@PathVariable("id") int id) {
		MovieDTO movie = new MovieDTO();
		try {
			Movie movieModel = movieDAO.getMovieById(id);
			movie.copy(movieModel);
		} catch (Exception e) {
			AppLogger.error("Exception occurred in getMovieById", e);
			return new ResponseEntity<Integer>(-1, HttpStatus.OK);
		}
		return new ResponseEntity<MovieDTO>(movie, HttpStatus.OK);
	}
	
	@GetMapping(path = "/all")
	public @ResponseBody ResponseEntity<?> getAllMovies() {
		List<MovieDTO> movies = new ArrayList<MovieDTO>();
		try {
			List<Movie> movieModels = movieDAO.getAllMovies();
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