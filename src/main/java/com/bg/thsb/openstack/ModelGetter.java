package com.bg.thsb.openstack;


import org.openstack4j.api.OSClient;
import org.openstack4j.model.compute.Server;
import org.openstack4j.openstack.OSFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class ModelGetter {

	public static void main(String[] args) {
		OSClient os = OSFactory.builder()
			.endpoint(Configuration.ENDPOINT)
			.credentials(Configuration.USER, Configuration.PASS)
			.tenantName(Configuration.TENANAT)
			.authenticate();

		List<? extends Server> servers = os.compute().servers().list();
		System.out.println(servers);

		try {
			FileOutputStream fileOutputStream = new FileOutputStream(Configuration.SERVERS_FILE);
			ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
			oos.writeObject(servers);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



}
