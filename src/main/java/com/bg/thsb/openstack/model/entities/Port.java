package com.bg.thsb.openstack.model.entities;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.openstack4j.model.network.State;

public class Port extends CachedResource{
	private State state;
	private Set<String> fixedIps;
	private Map<String, String> allowedAddressPairs;
	private String macAddress;
	private List<SecurityGroup> securityGroups;
	private String name;
	private String id;
	private Network network;
	private ResourceEntity device;
	private String tenantId;

	public State getState() {
		return state;
	}

	public Network getNetwork() {
		return network;
	}

	public ResourceEntity getDevice() {
		return device;
	}

	public Set<String> getFixedIps() {
		return fixedIps;
	}

	public Map<String, String> getAllowedAddressPairs() {
		return allowedAddressPairs;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public List<SecurityGroup> getSecurityGroups() {
		return securityGroups;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getType() {
		return "port";
	}
}
