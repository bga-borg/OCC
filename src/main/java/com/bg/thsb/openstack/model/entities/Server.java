package com.bg.thsb.openstack.model.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Server implements ResourceEntity {
	private String id;
	private String name;
	private Status status;
	private Map<String, String> metaData = Maps.newHashMap();
	private List<SecurityGroup> securityGroups = Lists.newArrayList();
	private Date launchedAt;
	private Date terminatedAt;
	private Port port;
	private Volume volume;
	private String imageId;
	private String flavorId;

	public Server() {
	}

	private Server(String id, String name, Status status,
		Date launchedAt, Date terminatedAt, Port port, Volume volume, String imageId,
		String flavorId) {
		this();
		this.id = id;
		this.name = name;
		this.status = status;
		this.launchedAt = launchedAt;
		this.terminatedAt = terminatedAt;
		this.port = port;
		this.volume = volume;
		this.imageId = imageId;
		this.flavorId = flavorId;
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

	public Volume getVolume() {
		return volume;
	}

	public void setVolume(Volume v) {
		this.volume = v;
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

	public Status getStatus() {
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


	public static class ServerBuilder {

		private String id;
		private String name;
		private Status status;
		private Date launchedAt;
		private Date terminatedAt;
		private Port port;
		private Volume volume;
		private String imageId;
		private String flavorId;

		public ServerBuilder setId(String id) {
			this.id = id;
			return this;
		}

		public ServerBuilder setName(String name) {
			this.name = name;
			return this;
		}

		public ServerBuilder setStatus(Status status) {
			this.status = status;
			return this;
		}

		public ServerBuilder setLaunchedAt(Date launchedAt) {
			this.launchedAt = launchedAt;
			return this;
		}

		public ServerBuilder setTerminatedAt(Date terminatedAt) {
			this.terminatedAt = terminatedAt;
			return this;
		}

		public ServerBuilder setPort(Port port) {
			this.port = port;
			return this;
		}

		public ServerBuilder setVolume(Volume volume) {
			this.volume = volume;
			return this;
		}

		public ServerBuilder setImageId(String imageId) {
			this.imageId = imageId;
			return this;
		}

		public ServerBuilder setFlavorId(String flavorId) {
			this.flavorId = flavorId;
			return this;
		}

		public Server build() {
			return new Server(id, name, status, launchedAt, terminatedAt, port, volume, imageId, flavorId);
		}
	}


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
		/** The server is powered off and the disk imageId still persists. */
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

	@Override
	public String toString() {
		return "Server{" +
			"id='" + id + '\'' +
			", name='" + name + '\'' +
			", status=" + status +
			", metaData=" + metaData +
			", securityGroups=" + securityGroups +
			", launchedAt=" + launchedAt +
			", terminatedAt=" + terminatedAt +
			", port=" + port +
			", volume=" + volume +
			", imageId='" + imageId + '\'' +
			", flavorId='" + flavorId + '\'' +
			'}';
	}
}
