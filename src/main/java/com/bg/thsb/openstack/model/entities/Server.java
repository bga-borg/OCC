package com.bg.thsb.openstack.model.entities;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.openstack4j.model.compute.Server.Status;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Server extends CachedResource {
	private String id;
	private String name;
	private Status status;
	private Map<String, String> metaData = Maps.newHashMap();
	private List<SecurityGroup> securityGroups = Lists.newArrayList();
	public String tenantId;
	private Date launchedAt;
	private Date terminatedAt;
	private Port port;
	private List<String> attachedVolumeIds;
	private String imageId;

	private String flavorId;

	public Server() {
	}

	public Map<String, String> getMetaData() {
		return metaData;
	}

	public void setMetaData(Map<String, String> metaData) {
		this.metaData = metaData;
	}

	public void setSecurityGroups(List<SecurityGroup> securityGroups) {
		this.securityGroups = securityGroups;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public void setLaunchedAt(Date launchedAt) {
		this.launchedAt = launchedAt;
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
		return "server";
	}

	public Port getPort() {
		return port;
	}

	public void setPort(Port p) {
		this.port = p;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public String getFlavorId() {
		return flavorId;
	}

	public void setFlavorId(String flavorId) {
		this.flavorId = flavorId;
	}

	public org.openstack4j.model.compute.Server.Status getStatus() {
		return status;
	}

	public void setStatus(Status s) {
		this.status = s;
	}

	public Map<String, String> getMetadata() {
		return metaData;
	}

	public List<SecurityGroup> getSecurityGroups() {
		return this.securityGroups;
	}

	public Date getLaunchedAt() {
		return launchedAt;
	}

	public void setLauchedAt(Date lA) {
		this.launchedAt = lA;
	}

	public Date getTerminatedAt() {
		return terminatedAt;
	}

	public void setTerminatedAt(Date tA) {
		this.terminatedAt = tA;
	}

	public List<String> getAttachedVolumeIds() {
		return attachedVolumeIds;
	}

	public void setAttachedVolumeIds(List<String> attachedVolumeIds) {
		this.attachedVolumeIds = attachedVolumeIds;
	}

	@Override
	public String toString() {
		return "Server{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", status=" + status +
				", metaData=" + metaData +
				", securityGroups=" + securityGroups +
				", tenantId='" + tenantId + '\'' +
				", launchedAt=" + launchedAt +
				", terminatedAt=" + terminatedAt +
				", port=" + port +
				", attachedVolumeIds=" + attachedVolumeIds +
				", imageId='" + imageId + '\'' +
				", flavorId='" + flavorId + '\'' +
				'}';
	}
}
