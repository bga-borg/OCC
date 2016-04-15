package com.bg.thsb.openstack;


import org.openstack4j.model.compute.Server;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

public class SerializedServerListLoader {

	//		List<? extends Server> servers = os.compute().servers().list();
	//		System.out.println(servers);
		/*try {
			FileOutputStream fileOutputStream = new FileOutputStream(OpenCloudCacheConfiguration.SERVERS_FILE);
			ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
			oos.writeObject(servers);
		} catch (IOException e) {
			e.printStackTrace();
		}*/


	public static void main(String[] args) throws Exception {
		/*FileInputStream fileInputStream = new FileInputStream(OpenCloudCacheConfiguration.SERVERS_FILE);
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		List<? extends Server> servers = (List<? extends Server>) objectInputStream.readObject();

		System.out.println(servers);*/
	}
}
