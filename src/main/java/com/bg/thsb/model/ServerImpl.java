package com.bg.thsb.model;

import com.bg.thsb.model.ifaces.Server;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class ServerImpl implements Server {
	private String id;
	private String name;
	private List<String> addresses;
	private String imageId;
	private String flavourId;
	private Status status;
	private Map<String, String> metaData;
	private List<String> securityGroups;
	private Date launchedAt;
	private Date terminatedAt;
	private String portId;
	private String volumeId;

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getPortId() {
		return portId;
	}

	@Override
	public String getVolumeId() {
		return volumeId;
	}

	@Override
	public String getImageId() {
		return imageId;
	}

	@Override
	public String getFlavorId() {
		return flavourId;
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
	public List<String> getSecurityGroupIds() {
		return securityGroups;
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
