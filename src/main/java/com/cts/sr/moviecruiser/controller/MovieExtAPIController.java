package com.cts.sr.moviecruiser.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cts.sr.moviecruiser.data.MovieDTO;
import com.cts.sr.moviecruiser.utils.AppLogger;
import com.cts.sr.moviecruiser.utils.MovieUtils;

@Controller
@RequestMapping(path = "/movie/api/")
@CrossOrigin(origins = "*")
public class MovieExtAPIController {
	
	@Autowired
	private ConfigurableEnvironment env;
	
	@GetMapping(path = "/search/{searchString}")
	public @ResponseBody ResponseEntity<List<MovieDTO>> search(@PathVariable("searchString") String searchString) {
		List<MovieDTO> movieList = new ArrayList<MovieDTO>();
		String endPointURL = MovieUtils.getAbsoluteURL(env.getProperty("SEARCH_URL"), new Object[]{searchString});
		try{
			movieList = MovieUtils.getMovies(endPointURL);
		}
		catch(Exception ex){
			AppLogger.error("Exception occurred in search", ex);
		}
		return new ResponseEntity<List<MovieDTO>>(movieList, HttpStatus.OK);
	}
	
	@GetMapping(path = "/upcoming")
	public @ResponseBody ResponseEntity<List<MovieDTO>> upcoming() {
		List<MovieDTO> movieList = new ArrayList<MovieDTO>();
		String endPointURL = MovieUtils.getAbsoluteURL(env.getProperty("UPCOMING_URL"), new Object[]{});
		try{
			movieList = MovieUtils.getMovies(endPointURL);
		}
		catch(Exception ex){
			AppLogger.error("Exception occurred in upcoming", ex);
		}
		return new ResponseEntity<List<MovieDTO>>(movieList, HttpStatus.OK);
	}
	
	@GetMapping(path = "/trending")
	public @ResponseBody ResponseEntity<List<MovieDTO>> trending() {
		List<MovieDTO> movieList = new ArrayList<MovieDTO>();
		String endPointURL = MovieUtils.getAbsoluteURL(env.getProperty("TRENDING_URL"), new Object[]{});
		try{
			movieList = MovieUtils.getMovies(endPointURL);
		}
		catch(Exception ex){
			AppLogger.error("Exception occurred in trending", ex);
		}
		return new ResponseEntity<List<MovieDTO>>(movieList, HttpStatus.OK);
	}
	
	@GetMapping(path = "/recommended/{id:[0-9]+}")
	public @ResponseBody ResponseEntity<List<MovieDTO>> recommended(@PathVariable("id") int id) {
		List<MovieDTO> movieList = new ArrayList<MovieDTO>();
		String endPointURL = MovieUtils.getAbsoluteURL(env.getProperty("RECOMMENDED_URL"), new Object[]{id});
		try{
			movieList = MovieUtils.getMovies(endPointURL);
		}
		catch(Exception ex){
			AppLogger.error("Exception occurred in recommended", ex);
		}
		return new ResponseEntity<List<MovieDTO>>(movieList, HttpStatus.OK);
	}
	
	
}