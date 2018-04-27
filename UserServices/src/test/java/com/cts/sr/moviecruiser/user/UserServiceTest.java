package com.cts.sr.moviecruiser.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cts.sr.moviecruiser.user.model.User;
import com.cts.sr.moviecruiser.user.repository.UserRepository;
import com.cts.sr.moviecruiser.user.service.UserServiceImpl;
import com.google.common.base.Optional;

public class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	private User user;

	@InjectMocks
	private UserServiceImpl userServiceImpl;

	Optional<User> optional;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		user = new User("LakshmiGandh", "Lakshmi", "Gandh","LakshmiGandh@gmail.com", "489706", new Date());
		optional = Optional.of(user);
	}
	

	@Test
	public void testValidLogin() throws Exception {
		when(userRepository.findByUserIdAndPassword(user.getUserId(), user.getPassword())).thenReturn(user);
		User dbUser = userServiceImpl.find(user.getUserId(), user.getPassword());
		assertNotNull(dbUser);
		assertEquals("LakshmiGandh", dbUser.getUserId());
		verify(userRepository, times(1)).findByUserIdAndPassword(user.getUserId(), user.getPassword());
	}

	@Test
	public void testInvalidLogin() throws Exception {
		when(userRepository.findById("LakshmiKanth")).thenReturn(null);
		User dbUser = userServiceImpl.find(user.getUserId(), user.getPassword());
		assertEquals(dbUser,null);
	}

	@Test
	public void testSaveUser() throws Exception{
		when(userRepository.save(user)).thenReturn(user);
		assertEquals("Cannot Register User", true, userServiceImpl.save(user));
		verify(userRepository, times(1)).save(user);
	}



}
