package com.cts.sr.moviecruiser.movie.service;

import java.util.List;

import com.cts.sr.moviecruiser.movie.model.Movie;

public interface MovieService {
	public boolean saveMovie(Movie movie) throws Exception;
	public boolean deleteMovie(long id) throws Exception;
	public Movie getMovieById(long id) throws Exception;
	public List<Movie> getAllMovies(String userId) throws Exception;
}