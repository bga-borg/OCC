package com.bg.thsb.openstack.model.impl;

import com.bg.thsb.openstack.model.ifaces.SecurityGroupRule;

public class SecurityGroupRuleImpl implements SecurityGroupRule {
	private Integer portRangeMax;
	private Integer portRangeMin;
	private String protocol;
	private String remoteIpPrefix;
	private String id;
	private String name;

	@Override
	public Integer getPortRangeMax() {
		return portRangeMax;
	}

	@Override
	public Integer getPortRangeMin() {
		return portRangeMin;
	}

	@Override
	public String getProtocol() {
		return protocol;
	}

	@Override
	public String getRemoteIpPrefix() {
		return remoteIpPrefix;
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
