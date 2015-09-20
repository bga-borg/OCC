package com.bg.thsb.model;

import com.bg.thsb.model.ifaces.Network;
import com.bg.thsb.model.ifaces.Port;
import com.bg.thsb.model.ifaces.ResourceEntity;
import com.bg.thsb.model.ifaces.SecurityGroup;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class PortImpl implements Port {
	private Network.State state;
	private Set<String> fixedIps;
	private Map<String, String> allowedAddressPairs;
	private String macAddress;
	private List<SecurityGroup> securityGroups;
	private String name;
	private String id;
	private Network network;
	private ResourceEntity device;

	@Override
	public Network.State getState() {
		return state;
	}

	@Override
	public Network getNetwork() {
		return network;
	}

	@Override
	public ResourceEntity getDevice() {
		return device;
	}

	@Override
	public Set<String> getFixedIps() {
		return fixedIps;
	}

	@Override
	public Map<String, String> getAllowedAddressPairs() {
		return allowedAddressPairs;
	}

	@Override
	public String getMacAddress() {
		return macAddress;
	}

	@Override
	public List<SecurityGroup> getSecurityGroups() {
		return securityGroups;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}
}
