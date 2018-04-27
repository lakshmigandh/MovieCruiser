package com.cts.sr.moviecruiser.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.sr.moviecruiser.user.model.User;

public interface UserRepository extends JpaRepository<User, String> {
	public User findByUserIdAndPassword(String userId, String password);
	public User findByUserId(String userId);
}
