package com.cts.sr.moviecruiser.user.service;

import java.util.Map;

import com.cts.sr.moviecruiser.user.model.User;

public interface JWTTokenGenerator {

	Map<String, String> generateToken(User user);
}
