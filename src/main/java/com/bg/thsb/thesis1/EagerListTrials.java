package com.bg.thsb.thesis1;

import com.bg.thsb.eagercollection.TrialMethod;
import com.bg.thsb.infinispan.InfinispanCacheWrapper;
import com.bg.thsb.plainmodel.Server;
import com.bg.thsb.plainmodel.Volume;
import com.google.common.collect.Lists;
import org.infinispan.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class EagerListTrials {

	public static final Logger logger = LoggerFactory.getLogger(EagerListTrials.class);
	Cache<String, Object> cache;

	public Server server1 = null;
	public Server server2 = null;

	@PostConstruct
	public void init() {
		cache = InfinispanCacheWrapper.getCache();
	}

	@TrialMethod
	public void trial2Server8Volume() throws InterruptedException {
		if (server1 == null)
			server1 = new Server.ServerBuilder().setName("Server 1").build();
		if (server2 == null)
			server2 = new Server.ServerBuilder().setName("Server 2").build();

		cache.put(server1.getId(), server1, -1, TimeUnit.SECONDS);
		cache.put(server2.getId(), server2, -1, TimeUnit.SECONDS);

		List<Volume> volumes = Lists.newArrayList();
		for (int i = 0; i < 8; i++) {
			volumes.add(new Volume.VolumeBuilder().setName("Volume " + i).build());
		}

		for (int i = 0; i < volumes.size(); i++) {
			if ((i % 2) == 1) {
				server1.getVolumes().add(volumes.get(i));
				Thread.sleep(1100);
			} else {
				server2.getVolumes().add((volumes.get(i)));
				Thread.sleep(1150);
			}
		}
	}

	@TrialMethod
	public void trial1Server4Volume() throws InterruptedException {
		if (server1 == null)
			server1 = new Server.ServerBuilder().setName("Server 1").build();
		cache.put(server1.getId(), server1, -1, TimeUnit.SECONDS);

		List<Volume> volumes = Lists.newArrayList();
		for (int i = 0; i < 4; i++) {
			volumes.add(new Volume.VolumeBuilder().setName("Volume " + (i + 1)).build());
			server1.getVolumes().add(volumes.get(i));
			Thread.sleep(1500);
		}
	}

	@TrialMethod
	public void trial1Server4VolumeRemoveOne() throws InterruptedException {
		if (server1 == null)
			server1 = new Server.ServerBuilder().setName("Server 1").build();
		cache.put(server1.getId(), server1, -1, TimeUnit.SECONDS);

		List<Volume> volumes = Lists.newArrayList();
		for (int i = 0; i < 4; i++) {
			volumes.add(new Volume.VolumeBuilder().setName("Volume " + (i + 1)).build());
			server1.getVolumes().add(volumes.get(i));
			Thread.sleep(1000);
		}

		server1.getVolumes().remove(1);
	}

	public void cleanupServers() {
		cache.clear();
		server1 = server2 = null;
	}
}
