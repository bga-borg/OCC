package com.bg.thsb.plainmodel;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * PlainModelFactory
 */
public class PlainModelFactory {
	public static Server getPlainServer() {
		Image image = new Image();
		image.setName("MyImage1");

		List<Volume> volumes = Lists.newArrayList();
		for (int i = 0; i < 100; i++) {
			volumes.add(new Volume.VolumeBuilder().setName("MyVolume" + i).build());
		}

		Server server = new Server.ServerBuilder()
			.setName("MyServer1")
			.setImage(image)
			.setVolumes(volumes)
			.build();

		return server;
	}

}
