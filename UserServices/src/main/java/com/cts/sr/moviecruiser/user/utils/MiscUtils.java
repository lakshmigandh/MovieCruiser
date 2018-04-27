package com.cts.sr.moviecruiser.user.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MiscUtils {

	public static String objectAsJSON(Object obj){
		String jsonString = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			jsonString = mapper.writeValueAsString(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonString;

	}

}
