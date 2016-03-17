package com.bg.thsb.openstack.model.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.base.CaseFormat;

import java.util.Date;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

public class Volume implements ResourceEntity {
	private String id;
	private String name;
	private Status status;
	private int size;
	private Date created;
	private String imageRef;
	private Map<String, String> metaData;
	private String sourceVolid;

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
		return "volume";
	}

	public Status getStatus() {
		return status;
	}

	public int getSize() {
		return size;
	}

	public Date getCreated() {
		return created;
	}

	public String getImageRef() {
		return imageRef;
	}

	public String getSourceVolid() {
		return sourceVolid;
	}

	public Map<String, String> getMetaData() {
		return metaData;
	}


	/**
	 * The current Volume Status
	 *
	 */
	enum Status {
		AVAILABLE, ATTACHING, BACKING_UP, CREATING, DELETING, DOWNLOADING, UPLOADING, ERROR, ERROR_DELETING, ERROR_RESTORING, IN_USE, RESTORING_BACKUP, UNRECOGNIZED;

		@JsonCreator
		public static Status fromValue(String status) {
			try {
				return valueOf(CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_UNDERSCORE, checkNotNull(status, "status")));
			} catch (IllegalArgumentException e) {
				return UNRECOGNIZED;
			}
		}

		@JsonValue
		public String value() {
			return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_HYPHEN, name());
		}

		@Override
		public String toString() {
			return value();
		}
	}
}
