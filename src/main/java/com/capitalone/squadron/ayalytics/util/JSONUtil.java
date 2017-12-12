package com.capitalone.squadron.ayalytics.util;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JSONUtil {

	public String convertListToJSON(List<Object> object)
			throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		String arrayToJson = objectMapper.writeValueAsString(object);
		return arrayToJson;
	}
}
