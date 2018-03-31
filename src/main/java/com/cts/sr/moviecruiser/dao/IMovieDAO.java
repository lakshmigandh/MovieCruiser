package com.cts.sr.moviecruiser.dao;

import java.util.List;

import com.cts.sr.moviecruiser.model.Movie;

public interface IMovieDAO {
	public boolean saveMovie(Movie movie) throws Exception;
	public boolean deleteMovie(long id) throws Exception;
	public Movie getMovieById(long id) throws Exception;
	public List<Movie> getAllMovies() throws Exception;
}