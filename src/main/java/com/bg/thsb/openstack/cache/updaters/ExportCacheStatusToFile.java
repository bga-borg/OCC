package com.bg.thsb.openstack.cache.updaters;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.catalina.Server;
import org.apache.log4j.Logger;
import org.openstack4j.core.transport.ObjectMapperSingleton;
import org.openstack4j.model.identity.Tenant;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * ConsoleUpdater
 *
 */
@Service
public class ExportCacheStatusToFile extends CacheUpdater {
	private static final Logger logger = Logger.getLogger(ExportCacheStatusToFile.class);

	ObjectMapper getObjectMapper(){
		ObjectMapper rootMapper = new ObjectMapper();
//		rootMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		rootMapper.enable(SerializationFeature.INDENT_OUTPUT);
		rootMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
		rootMapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
		rootMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
		rootMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		return rootMapper;
	}

	@Override
	public void run() {

		BufferedWriter writer = null;
		try {

			//create a temporary file
			String timeLog = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			File logFile = new File(timeLog);

			// This will output the full path where the file will be written to...
			System.out.println(logFile.getCanonicalPath());
			logger.info("Writing cache content to " + logFile.getCanonicalPath());
			writer = new BufferedWriter(new FileWriter(logFile));

			Map<String, Object> stringObjectMap = new HashMap<>();
			for (String key : cache.keySet()) {
				stringObjectMap.put(key, cache.get(key));
			}
			writer.write(getObjectMapper().writeValueAsString(stringObjectMap));
			logger.info(this.getClass().getName() + " refreshed");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Close the writer regardless of what happens...
				writer.close();
			} catch (Exception e) {
			}
		}
	}
}
