package com.bg.thsb.openstack;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:openstack.properties")
public class OpenStackConfiguration {

	@Value("${openstack.endpoint}")
	String endpoint;

	@Value("${openstack.user}")
	String user;

	@Value("${openstack.useradmin}")
	String userAdmin;

	@Value("${openstack.pass}")
	String userPass;

	@Value("${openstack.tenant}")
	String tenant;

	public String getEndpoint() {
		return endpoint;
	}

	public String getUser() {
		return user;
	}

	public String getUserAdmin() {
		return userAdmin;
	}

	public String getUserPass() {
		return userPass;
	}

	public String getTenant() {
		return tenant;
	}
}