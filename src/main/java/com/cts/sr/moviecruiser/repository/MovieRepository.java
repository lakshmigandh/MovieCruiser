package com.cts.sr.moviecruiser.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.sr.moviecruiser.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {}