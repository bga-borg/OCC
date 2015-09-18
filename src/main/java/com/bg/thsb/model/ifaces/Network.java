package com.bg.thsb.model.ifaces;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.openstack4j.common.Buildable;
import org.openstack4j.model.common.Resource;
import org.openstack4j.model.network.builder.NetworkBuilder;

import java.util.List;

/**
 * An OpenStack (Neutron) network
 *
 * @author Jeremy Unruh
 */
public interface Network extends Resource, Buildable<NetworkBuilder> {

	/**
	 * @return the status of the network
	 */
	State getStatus();

	/**
	 * @return list of subnet identifiers associated with the network
	 */
	List<String> getSubnets();

	/**
	 * @return the physical network provider or null
	 */
	String getProviderPhyNet();

	/**
	 * @return true if the administrative state is up
	 */
	boolean isAdminStateUp();

	/**
	 * @return the network type
	 */
	NetworkType getNetworkType();

	/**
	 * @return true if the router is external
	 */
	boolean isRouterExternal();

	/**
	 * @return true if the network is shared
	 */
	boolean isShared();


	enum State {
		ACTIVE,
		DOWN,
		BUILD,
		ERROR,
		PENDING_CREATE,
		PENDING_UPDATE,
		PENDING_DELETE,
		UNRECOGNIZED;

		State() {
		}

		@JsonCreator
		public static State forValue(String value) {
			if (value != null) {
				State[] arr$ = values();
				int len$ = arr$.length;

				for (int i$ = 0; i$ < len$; ++i$) {
					State s = arr$[i$];
					if (s.name().equalsIgnoreCase(value)) {
						return s;
					}
				}
			}

			return UNRECOGNIZED;
		}
	}


	enum NetworkType {
		LOCAL,
		FLAT,
		VLAN,
		VXLAN,
		GRE;

		NetworkType() {
		}

		@JsonCreator
		public static NetworkType forValue(String value) {
			if (value != null) {
				NetworkType[] arr$ = values();
				int len$ = arr$.length;

				for (int i$ = 0; i$ < len$; ++i$) {
					NetworkType s = arr$[i$];
					if (s.name().equalsIgnoreCase(value)) {
						return s;
					}
				}
			}

			return null;
		}

		@JsonValue
		public String toJson() {
			return this.name().toLowerCase();
		}
	}

}
