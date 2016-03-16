package com.bg.thsb.openstack.model.entities;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Date;
import java.util.Map;

public class Image implements ResourceEntity {
	private String id;
	private String name;
	private long size;
	private int minDisk;
	private int minRam;
	private Status status;
	private Date created;
	private Map<String, Object> metaData;
	private boolean isSnapsot;

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

	public long getSize() {
		return size;
	}

	public int getMinDisk() {
		return minDisk;
	}

	public int getMinRam() {
		return minRam;
	}

	public Status getStatus() {
		return status;
	}

	public Date getCreated() {
		return created;
	}

	public Map<String, Object> getMetaData() {
		return metaData;
	}

	public boolean isSnapshot() {
		return isSnapsot;
	}

	/**
	 * Status can be used while an image is being saved.  It provides state of the progress indicator.  Images with ACTIVE status
	 * are available for install.
	 */
	enum Status {
		UNRECOGNIZED, UNKNOWN, ACTIVE, SAVING, ERROR, DELETED;

		@JsonCreator
		public static Status forValue(String value) {
			if (value != null) {
				for (Status s : Status.values()) {
					if (s.name().equalsIgnoreCase(value))
						return s;
				}
			}
			return Status.UNKNOWN;
		}
	}
}
