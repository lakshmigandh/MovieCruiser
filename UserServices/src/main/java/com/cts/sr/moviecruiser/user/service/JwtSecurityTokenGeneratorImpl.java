package com.cts.sr.moviecruiser.user.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cts.sr.moviecruiser.user.model.User;
import com.cts.sr.moviecruiser.user.utils.Constants;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtSecurityTokenGeneratorImpl implements JWTTokenGenerator,Constants {

	@Override
	public Map<String, String> generateToken(User user) {
		String jwtToken = null;
		jwtToken = Jwts.builder().setSubject(user.getUserId()).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, JWT_SECRET_KEY).compact();
		Map<String, String> map = new HashMap<>();
		map.put(JWT_TOKEN, jwtToken);
		return map;
	}

}
