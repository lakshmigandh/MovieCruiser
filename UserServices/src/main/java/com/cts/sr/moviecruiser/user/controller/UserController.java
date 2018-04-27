package com.cts.sr.moviecruiser.user.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.cts.sr.moviecruiser.user.data.UserDTO;
import com.cts.sr.moviecruiser.user.model.User;
import com.cts.sr.moviecruiser.user.service.JWTTokenGenerator;
import com.cts.sr.moviecruiser.user.service.UserService;
import com.cts.sr.moviecruiser.user.utils.Constants;
import com.cts.sr.moviecruiser.user.utils.ErrorCodes;

import io.swagger.annotations.Api;

@RestController
@EnableWebMvc
@RequestMapping("/user")
@CrossOrigin("*")
@Api(tags={"User Controller"})
public class UserController implements Constants {

	@Autowired
	private UserService userService;

	@Autowired
	private JWTTokenGenerator tokenGenerator;
	
	
	@GetMapping("/exists/{userId}")
	public ResponseEntity<?> exists(@PathVariable String userId) {
		try {
			User user = userService.find(userId);
			if(user == null)
				return new ResponseEntity<Boolean>(false, HttpStatus.OK);
			else
				return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Integer>(-1, HttpStatus.CONFLICT);
		}
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody UserDTO user) {
		try {
			if(user == null || StringUtils.isEmpty(user.getUserId())
					|| StringUtils.isEmpty(user.getFirstName()) || StringUtils.isEmpty(user.getLastName())
					|| StringUtils.isEmpty(user.getEmail()) || StringUtils.isEmpty(user.getPassword())){
				return new ResponseEntity<Integer>(-2, HttpStatus.OK);
			}
			
			User newUser = new User();
			newUser.copy(user);
			userService.save(newUser);
			return new ResponseEntity<Integer>(0, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Integer>(-1, HttpStatus.CONFLICT);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserDTO loggedInUser) {
		try {
			String userId = loggedInUser.getUserId();
			String password = loggedInUser.getPassword();

			
			if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(password)) 
				return new ResponseEntity<ErrorCodes>(ErrorCodes.USER_ID_PASSWORD_EMPTY, HttpStatus.OK);

			User user = userService.find(userId, password);
			
			if (user == null) {
				return new ResponseEntity<ErrorCodes>(ErrorCodes.USER_DOES_NOT_EXIST, HttpStatus.OK);
			}
			String pass = user.getPassword();
			
			if (!password.equals(pass)) {
				return new ResponseEntity<ErrorCodes>(ErrorCodes.PASSWORD_INCORRECT, HttpStatus.OK);
			}

			Map<String, String> map = tokenGenerator.generateToken(user);
			map.put(USER_ID, user.getUserId());
			map.put(USER_FIRST_NAME, user.getFirstName());
			map.put(USER_LAST_NAME, user.getLastName());
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<ErrorCodes>(ErrorCodes.UNEXPECTED_ERROR, HttpStatus.OK);
		}
	}

}
