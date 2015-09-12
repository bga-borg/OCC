package com.bg.thsb.openstack;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.openstack4j.model.compute.Server;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonLoader {


	public static void main(String[] args) {
		ObjectMapper mapper = new ObjectMapper();

		List<? extends Server> servers = Lists.newArrayList();

		try {
			servers = mapper.readValue(new File(Configuration.SERVERS_JSON_FILE),
				mapper.getTypeFactory().constructCollectionType(List.class, Server.class));
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(servers);
	}
}
