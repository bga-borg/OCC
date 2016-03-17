package com.bg.thsb.openstack.model.entities;

import org.openstack4j.model.storage.block.Volume;

import java.util.Date;
import java.util.Map;

public class VolumeSnapshot implements ResourceEntity {

	private String volumeId;
	private Volume.Status status;
	private int size;
	private Date created;
	private Map<String, String> metaData;
	private String id;
	private String name;

	public String getVolumeId() {
		return volumeId;
	}

	public Volume.Status getStatus() {
		return status;
	}

	public int getSize() {
		return size;
	}

	public Date getCreated() {
		return created;
	}

	public Map<String, String> getMetaData() {
		return metaData;
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
		return "volumeSnapshot";
	}
}
