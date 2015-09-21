package com.bg.thsb.openstack.model.impl;

import com.bg.thsb.openstack.model.ifaces.VolumeSnapshot;
import org.openstack4j.model.storage.block.Volume;

import java.util.Date;
import java.util.Map;

public class VolumeSnapshotImpl implements VolumeSnapshot {

	private String volumeId;
	private Volume.Status status;
	private int size;
	private Date created;
	private Map<String, String> metaData;
	private String id;
	private String name;

	@Override
	public String getVolumeId() {
		return volumeId;
	}

	@Override
	public Volume.Status getStatus() {
		return status;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public Date getCreated() {
		return created;
	}

	@Override
	public Map<String, String> getMetaData() {
		return metaData;
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
