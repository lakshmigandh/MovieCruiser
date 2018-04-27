package com.cts.sr.moviecruiser.movie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.cts.sr.moviecruiser.movie.filter.JwtFilter;

@SpringBootApplication
public class MoviecruiserApplication {
	
	@Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new JwtFilter());
		registrationBean.addUrlPatterns("/movie/*");
		return registrationBean;
	}

	public static void main(String[] args) {
		SpringApplication.run(MoviecruiserApplication.class, args);
	}
}
