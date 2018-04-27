package com.cts.sr.moviecruiser.user.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.sr.moviecruiser.user.model.User;
import com.cts.sr.moviecruiser.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean save(User user) throws Exception {
		Optional<User> dbUser = userRepository.findById(user.getUserId());
		if (dbUser.isPresent()) 
			return false;
		userRepository.save(user);
		return true;
	}

	@Override
	public User find(String userId, String password) throws Exception {
		User user = userRepository.findByUserIdAndPassword(userId, password);
		return user;
	}

	@Override
	public User find(String userId) throws Exception {
		User user = userRepository.findByUserId(userId);
		return user;
	}

}
