package com.bg.thsb.openstack.model.impl;

import com.bg.thsb.openstack.model.ifaces.SecurityGroup;
import com.bg.thsb.openstack.model.ifaces.SecurityGroupRule;

import java.util.List;

public class SecurityGroupImpl implements SecurityGroup {
	private String description;
	private List<? extends SecurityGroupRule> rules;
	private String name;
	private String id;

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public List<? extends SecurityGroupRule> getRules() {
		return rules;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}
}
