package com.bg.thsb.model;

import com.bg.thsb.model.ifaces.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class ServerImpl implements Server {
	private String id;
	private String name;
	private List<String> addresses;
	private Status status;
	private Map<String, String> metaData;
	private List<SecurityGroup> securityGroups;
	private Date launchedAt;
	private Date terminatedAt;
	private Port port;
	private Volume volume;
	private Image image;

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

	@Override
	public Port getPort() {
		return port;
	}

	@Override
	public Volume getVolume() {
		return volume;
	}

	@Override
	public Image getImage() {
		return image;
	}

	@Override
	public Flavor getFlavor() {
		return null;
	}

	@Override
	public Status getStatus() {
		return status;
	}

	@Override
	public Map<String, String> getMetadata() {
		return metaData;
	}

	@Override
	public List<SecurityGroup> getSecurityGroups() {
		return this.securityGroups;
	}

	@Override
	public Date getLaunchedAt() {
		return launchedAt;
	}

	@Override
	public Date getTerminatedAt() {
		return terminatedAt;
	}

}
