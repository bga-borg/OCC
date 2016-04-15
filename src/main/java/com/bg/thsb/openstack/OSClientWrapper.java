package com.bg.thsb.openstack;


import org.openstack4j.api.OSClient;
import org.openstack4j.openstack.OSFactory;
import org.springframework.stereotype.Component;

@Component
public class OSClientWrapper {

	private static OSClient os = null;

	private OSClientWrapper() {
	}

	public synchronized static OSClient getOs() {
		OpenCloudCacheConfiguration openCloudCacheConfiguration = new OpenCloudCacheConfiguration();
		os = OSFactory.builder()
			.endpoint(openCloudCacheConfiguration.getEndpoint())
			.credentials(openCloudCacheConfiguration.getUser(), openCloudCacheConfiguration.getUserPass())
			.tenantName(openCloudCacheConfiguration.getTenant())
			.authenticate();
		return os;
	}
}

//		List<? extends Server> servers = os.compute().servers().list();
//		System.out.println(servers);
		/*try {
			FileOutputStream fileOutputStream = new FileOutputStream(OpenCloudCacheConfiguration.SERVERS_FILE);
			ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
			oos.writeObject(servers);
		} catch (IOException e) {
			e.printStackTrace();
		}*/
