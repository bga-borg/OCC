package com.bg.thsb.openstack.model.entities;

public class SecurityGroupRule implements ResourceEntity {
	private Integer portRangeMax;
	private Integer portRangeMin;
	private String protocol;
	private String remoteIpPrefix;
	private String id;
	private String name;

	public Integer getPortRangeMax() {
		return portRangeMax;
	}

	public Integer getPortRangeMin() {
		return portRangeMin;
	}

	public String getProtocol() {
		return protocol;
	}

	public String getRemoteIpPrefix() {
		return remoteIpPrefix;
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
}
