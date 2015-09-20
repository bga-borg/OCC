package com.bg.thsb.model;

import com.bg.thsb.model.ifaces.Volume;

import java.util.Date;
import java.util.Map;

public class VolumeImpl implements Volume {
	private String id;
	private String name;
	private Status status;
	private int size;
	private Date created;
	private String imageRef;
	private Map<String, String> metaData;
	private String sourceVolid;

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
	public Status getStatus() {
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
	public String getImageRef() {
		return imageRef;
	}

	@Override
	public String getSourceVolid() {
		return sourceVolid;
	}

	@Override
	public Map<String, String> getMetaData() {
		return metaData;
	}

}
