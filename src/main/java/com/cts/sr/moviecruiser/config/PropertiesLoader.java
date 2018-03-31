package com.cts.sr.moviecruiser.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:/movie.ext.api.properties")
public class PropertiesLoader{}