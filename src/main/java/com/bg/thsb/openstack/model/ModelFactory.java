package com.bg.thsb.openstack.model;

import com.bg.thsb.openstack.model.ifaces.Flavor;
import com.bg.thsb.openstack.model.ifaces.Server;
import com.bg.thsb.openstack.model.impl.FlavorImpl;
import com.bg.thsb.openstack.model.impl.ServerImpl;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collection;

public class ModelFactory {

	public static Server getSimpleServer() {
		Server server = new ServerImpl.ServerBuilder()
			.setName("MyServer")
			.build();

		Flavor flavor = new FlavorImpl.FlavorBuilder().createFlavorImpl();

		server.getMetadata().put("key1", "value1");

		return server;
	}

	public static Collection<Server> getServerCollection() {
		throw new NotImplementedException();
	}

}
