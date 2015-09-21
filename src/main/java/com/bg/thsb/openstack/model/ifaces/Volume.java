package com.bg.thsb.openstack.model.ifaces;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.base.CaseFormat;

import java.util.Date;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * An OpenStack Volume
 *
 * @author Jeremy Unruh
 */
public interface Volume extends ResourceEntity {

	/**
	 * @return the status of the volume
	 */
	Status getStatus();

	/**
	 * @return the size in GB of the volume
	 */
	int getSize();

	/**
	 * @return the created date of the volume
	 */
	Date getCreated();

	/**
	 * @return the image reference identifier (if an image was associated) otherwise null
	 */
	String getImageRef();

	/**
	 * @return ID of source volume to clone from
	 */
	String getSourceVolid();

	/**
	 * @return extended meta data information. key value pair of String key, String value
	 */
	Map<String, String> getMetaData();


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
