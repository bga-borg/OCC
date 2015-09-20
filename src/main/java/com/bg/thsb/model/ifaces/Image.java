package com.bg.thsb.model.ifaces;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Date;
import java.util.Map;

/**
 * An OpenStack image is a collection of files used to create a Server.  Users provide pre-built OS images by default and or custom
 * images can be built
 *
 * @author Jeremy Unruh
 */
public interface Image extends ResourceEntity {

	/**
	 * @return the size in bytes
	 */
	long getSize();

	/**
	 * @return the minimum disk in bytes
	 */
	int getMinDisk();

	/**
	 * @return the minimum ram in bytes
	 */
	int getMinRam();

	/**
	 * @return the status of this image
	 */
	Status getStatus();

	/**
	 * @return the date the image was created
	 */
	Date getCreated();

	/**
	 * @return extra metadata/specs associated with the image
	 */
	Map<String, Object> getMetaData();

	/**
	 * Determines if this image is a snapshot
	 *
	 * @return true if this image is a snapshot
	 */
	boolean isSnapshot();

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
