package com.bg.thsb.openstack.model;

import com.bg.thsb.openstack.model.entities.Flavor;
import com.bg.thsb.openstack.model.entities.Server;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collection;

public class ModelFactory {

	public static Server getSimpleServer() {
		Server server = new Server.ServerBuilder()
			.setName("MyServer")
			.build();

		Flavor flavor = new Flavor.FlavorBuilder().createFlavorImpl();

		server.getMetadata().put("key1", "value1");

		return server;
	}

	public static Collection<Server> getServerCollection() {
		throw new NotImplementedException();
	}

}
