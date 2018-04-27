package com.cts.sr.moviecruiser.movie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.sr.moviecruiser.movie.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
	
	public List<Movie> findByUserId(String userId);
}