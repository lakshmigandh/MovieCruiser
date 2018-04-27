package com.cts.sr.moviecruiser.user;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.cts.sr.moviecruiser.user.controller.UserController;
import com.cts.sr.moviecruiser.user.data.UserDTO;
import com.cts.sr.moviecruiser.user.model.User;
import com.cts.sr.moviecruiser.user.service.JWTTokenGenerator;
import com.cts.sr.moviecruiser.user.service.UserService;
import com.cts.sr.moviecruiser.user.utils.MiscUtils;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMVC;

	@MockBean
	private UserService userService;
	
	@MockBean
	private JWTTokenGenerator securityTokenGenerator;

	private User user;
	private UserDTO userDTO;

	@InjectMocks
	UserController userController;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		user = new User("LakshmiGandh", "Lakshmi", "Gandh","LakshmiGandh@gmail.com", "489706", new Date());
		userDTO = new UserDTO();
		userDTO.copy(user);
	}
	
	@Test
	public void testLogin() throws Exception {

		String userId = "LakshmiGandh";
		String password = "489706";
		when(userService.save(user)).thenReturn(true);
		when(userService.find(userId, password)).thenReturn(user);
		mockMVC.perform(post("/user/login").contentType(MediaType.APPLICATION_JSON).content(MiscUtils.objectAsJSON(userDTO)))
				.andExpect(status().isOk());
		verify(userService, times(1)).find(user.getUserId(), user.getPassword());
		verifyNoMoreInteractions(userService);

	}

	@Test
	public void testRegister() throws Exception {
		when(userService.save(user)).thenReturn(true);
		mockMVC.perform(post("/user/register").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(MiscUtils.objectAsJSON((userDTO)))).andExpect(status().isCreated())
				.andDo(print());
		verify(userService, times(1)).save(Mockito.any(User.class));
		verifyNoMoreInteractions(userService);

	}	


}
