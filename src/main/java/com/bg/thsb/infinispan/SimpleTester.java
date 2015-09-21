package com.bg.thsb.infinispan;

import com.bg.thsb.openstack.model.ModelFactory;
import com.bg.thsb.openstack.model.ifaces.Server;

/**
 * SimpleTester
 *
 */
public class SimpleTester implements Runnable {

	public static void main(String[] args) {
		new SimpleTester().run();
	}

	@Override
	public void run() {
		Server server = ModelFactory.getSimpleServer();


	}
}
