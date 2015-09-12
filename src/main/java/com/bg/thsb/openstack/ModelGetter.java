package com.bg.thsb.openstack;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.openstack4j.api.OSClient;
import org.openstack4j.model.compute.Server;
import org.openstack4j.openstack.OSFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ModelGetter {

	public static void main(String[] args) {
		OSClient os = OSFactory.builder()
			.endpoint("http://10.41.49.175:5000/v2.0/")
			.credentials("devel", "develpass")
			.tenantName("devel")
			.authenticate();

		List<? extends Server> servers = os.compute().servers().list();
		System.out.println(servers);

		// save to json with jackson
		ObjectMapper mapper = new ObjectMapper();

		try {
			mapper.writerWithDefaultPrettyPrinter().writeValue(new File(Configuration.SERVERS_JSON_FILE), servers);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



}
