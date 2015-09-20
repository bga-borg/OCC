package com.bg.thsb.model.ifaces;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * A server is a virtual machine instance on a compute based system.  
 *
 * @author Jeremy Unruh
 */
public interface Server extends ResourceEntity {

	String getPortId();

	String getVolumeId();

	/**
	 * @return the image identifier
	 */
	String getImageId();

	/**
	 * @return the flavor identifier
	 */
	String getFlavorId();

	/**
	 * @return the status
	 */
	Status getStatus();


	/**
	 * @return the metadata
	 */
	Map<String, String> getMetadata();

	/**
	 * @return the securitygroups
	 */
	List<String> getSecurityGroupIds();

	/**
	 * @return the last time the server was launched
	 */
	Date getLaunchedAt();

	/**
	 * @return the last termination date
	 */
	Date getTerminatedAt();

	/**
	 * Servers contain a status attribute that can be used as an indication of the current server
	 * state. Servers with an ACTIVE status are available for use.
	 */
	enum Status {
		/** The server is active */
		ACTIVE,
		/** The server has not finished the original build process */
		BUILD,
		/** The server is currently being rebuilt */
		REBUILD,
		/** The server is suspended, either by request or necessity. This status appears for only the following hypervisors:
		 * XenServer/XCP, KVM, and ESXi. Administrative users may suspend an instance if it is infrequently used or to
		 * perform system maintenance. When you suspend an instance, its VM state is stored on disk, all memory is written to disk,
		 * and the virtual machine is stopped. Suspending an instance is similar to placing a device in hibernation;
		 * memory and vCPUs become available to create other instances.
		 */
		SUSPENDED,
		/** In a paused state, the state of the server is stored in RAM. A paused server continues to run in frozen state. */
		PAUSED,
		/** Server is performing the differential copy of data that changed during its initial copy. Server is down for this stage. */
		RESIZE,
		/** System is awaiting confirmation that the server is operational after a move or resize. */
		VERIFY_RESIZE,
		/** The resize or migration of a server failed for some reason. The destination server is being cleaned up and
		 *  the original source server is restarting. */
		REVERT_RESIZE,
		/** The password is being reset on the server. */
		PASSWORD,
		/** The server is in a soft reboot state. A reboot command was passed to the operating system. */
		REBOOT,
		/**  The server is hard rebooting. This is equivalent to pulling the power plug on a physical server,
		 *   plugging it back in, and rebooting it.
		 */
		HARD_REBOOT,
		/** The server is permanently deleted. */
		DELETED,
		/** The state of the server is unknown. Contact your cloud provider. */
		UNKNOWN,
		/** The server is in error. */
		ERROR,
		/** The server is powered off and the disk image still persists. */
		STOPPED,
		/** The virtual machine (VM) was powered down by the user, but not through the OpenStack Compute API. */
		SHUTOFF,
		/** The server is currently being migrated */
		MIGRATING,
		/** OpenStack4j could not find a Status mapping for the current reported Status.  File an issue indicating the missing state */
		UNRECOGNIZED;

		@JsonCreator
		public static Status forValue(String value) {
			if (value != null) {
				for (Status s : Status.values()) {
					if (s.name().equalsIgnoreCase(value))
						return s;
				}
			}
			return Status.UNRECOGNIZED;
		}

		@JsonValue
		public String value() {
			return name().toLowerCase();
		}
	}

}
