package com.bg.thsb.model;

import com.bg.thsb.model.ifaces.Network;
import com.bg.thsb.model.ifaces.Port;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class PortImpl implements Port {
	private Network.State state;
	private String networkId;
	private String deviceId;
	private String deviceOwnerId;
	private Set<String> fixedIps;
	private Map<String, String> allowedAddressPairs;
	private String macAddress;
	private List<String> securityGroups;
	private String tenantId;
	private String name;
	private String id;

	@Override
	public Network.State getState() {
		return state;
	}

	@Override
	public String getNetworkId() {
		return networkId;
	}

	@Override
	public String getDeviceId() {
		return deviceId;
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
	public List<String> getSecurityGroups() {
		return securityGroups;
	}
}
