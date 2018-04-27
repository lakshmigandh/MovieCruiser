package com.cts.sr.moviecruiser.movie.utils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.cts.sr.moviecruiser.movie.data.MovieDTO;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MovieUtils {
	
	public static List<MovieDTO> getMovies(String endPointURL){
		List<MovieDTO> movieList = new ArrayList<MovieDTO>();
		try{
			RestTemplate restTemplate = new RestTemplate();
			String jsonResponse = restTemplate.getForObject(endPointURL, String.class);
		    ObjectMapper om = new ObjectMapper();
		    om.configure(Feature.AUTO_CLOSE_SOURCE, true);
		    JsonNode jsonNode = om.readTree(jsonResponse);
		    JsonNode resultsNode = jsonNode.at("/results");
		    movieList = om.readValue(resultsNode.toString(), new TypeReference<List<MovieDTO>>(){});
		}
		catch(Exception ex){
			AppLogger.error("Exception occurred in getMovies", ex);
		}
	    return movieList;
	}
	
	public static String getAbsoluteURL(String url,Object[] args){
		return MessageFormat.format(url, args);
	}
	
	public static String getObjectAsString(Object obj){
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			AppLogger.error("Exception occurred in getObjectAsString", e);
		}
		return null;
	}

}
