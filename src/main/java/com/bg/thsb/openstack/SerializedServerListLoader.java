package com.bg.thsb.openstack;


import org.openstack4j.model.compute.Server;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

public class SerializedServerListLoader {


	public static void main(String[] args) throws Exception {
		FileInputStream fileInputStream = new FileInputStream(Configuration.SERVERS_JSON_FILE);
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		List<? extends Server> servers = (List<? extends Server>) objectInputStream.readObject();

		System.out.println(servers);
	}
}
