package com.cts.sr.moviecruiser.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cts.sr.moviecruiser.model.Movie;
import com.cts.sr.moviecruiser.repository.MovieRepository;

@Repository
@Transactional
public class MovieServiceImpl implements MovieService {
	
	
	@Autowired
	private MovieRepository movieRepository;
	
	public boolean saveMovie(Movie movie) throws Exception {
		try {
			movieRepository.save(movie);
		}
		catch(Exception ex) {
			throw ex;
		}
		return true;
	}
	
	public boolean deleteMovie(long id) throws Exception {
		
		Optional<Movie> movie = movieRepository.findById(id);
		
		if (movie.get() == null) {
			throw new Exception("Movie not found");
		}
		
		movieRepository.delete(movie.get());
		
		return true;
	}
	
	public Movie getMovieById(long id) throws Exception {
		Optional<Movie> movie = movieRepository.findById(id);
		
		if (movie.get() == null) {
			throw new Exception("Movie not found");
		}
		return movie.get();
	}
	@Override
	public List<Movie> getAllMovies() throws Exception {
		List<Movie> movies = movieRepository.findAll();
		return movies;
	}
}
