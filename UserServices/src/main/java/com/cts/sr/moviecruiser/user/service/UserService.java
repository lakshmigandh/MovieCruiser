package com.cts.sr.moviecruiser.user.service;

import com.cts.sr.moviecruiser.user.model.User;


public interface UserService {
	public User find(String userId, String password) throws Exception;
	public User find(String userId) throws Exception;
	public boolean save(User user) throws Exception;
}
