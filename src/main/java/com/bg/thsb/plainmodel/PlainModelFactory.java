package com.bg.thsb.plainmodel;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * PlainModelFactory
 */
public class PlainModelFactory {

	/**
	 * Returns a Server with 10 attached volumes as list
	 *
	 * @param nameOfServer
	 * @return
	 */
	public static Server getPlainServerWithVolumes(String nameOfServer) {

		List<Volume> volumes = Lists.newArrayList();
		for (int i = 0; i < 100; i++) {
			volumes.add(new Volume.VolumeBuilder().setName("MyVolume" + i).build());
		}

		Server server = new Server.ServerBuilder()
				.setName(nameOfServer)
			.setVolumes(volumes)
			.build();

		return server;
	}

}
