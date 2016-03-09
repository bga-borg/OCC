package com.bg.thsb.openstack.cache.updaters;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * ConsoleUpdater
 *
 */
@Service
public class ExportCacheStatusToFile extends CacheUpdater {
	private static final Logger logger = Logger.getLogger(ExportCacheStatusToFile.class);

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

			StringBuffer sb = new StringBuffer();
			for (String key : cache.keySet()) {
				sb.append(key + "\t=>\t" +cache.get(key) + "\n\n");
			}
			writer.write(sb.toString());
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
