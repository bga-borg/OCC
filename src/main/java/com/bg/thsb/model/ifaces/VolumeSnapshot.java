package com.bg.thsb.model.ifaces;

import org.openstack4j.model.storage.block.Volume.Status;

import java.util.Date;
import java.util.Map;

/**
 * An OpenStack Volume Snapshot which is a point-in-time copy of a volume
 *
 * @author Jeremy Unruh
 */
public interface VolumeSnapshot extends ResourceEntity {

	/**
	 * The volume identifier of an existing volume
	 *
	 * @return the volume identifier or null
	 */
	String getVolumeId();

	/**
	 * @return the status of the snapshot
	 */
	Status getStatus();

	/**
	 * Size in GBs
	 *
	 * @return the size of the snapshot in GB
	 */
	int getSize();

	/**
	 * @return the created data of the snapshot
	 */
	Date getCreated();

	/**
	 * @return extended meta data information. key value pair of String key, String value
	 */
	Map<String, String> getMetaData();
}
