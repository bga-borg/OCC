package com.occ.helper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * JsonConverter
 *
 */
public class JsonConverter {
	public static ObjectMapper getObjectMapper() {
		ObjectMapper rootMapper = new ObjectMapper();
		//		rootMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		rootMapper.enable(SerializationFeature.INDENT_OUTPUT);
		rootMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
		rootMapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
		rootMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
		rootMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		return rootMapper;
	}
}
