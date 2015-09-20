package com.bg.thsb.model;

import com.bg.thsb.model.ifaces.SecurityGroup;
import com.bg.thsb.model.ifaces.SecurityGroupRule;

import java.util.List;

public class SecurityGroupImpl implements SecurityGroup {
	private String description;
	private List<? extends SecurityGroupRule> rules;
	private String tenantId;
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
	public String getTenantId() {
		return tenantId;
	}

	@Override
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
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
