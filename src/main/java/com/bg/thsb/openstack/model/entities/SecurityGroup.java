package com.bg.thsb.openstack.model.entities;

import java.util.List;

public class SecurityGroup extends CachedResource {
	private String description;
	private List<? extends SecurityGroupRule> rules;
	private String name;
	private String id;

	public String getDescription() {
		return description;
	}

	public List<? extends SecurityGroupRule> getRules() {
		return rules;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getType() {
		return "securityGroup";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
