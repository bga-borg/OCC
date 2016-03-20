package com.bg.thsb.openstack.model.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.List;

public class Network extends CachedResource{
	private State status;
	private List<String> subnets;
	private String providerPhyNet;
	private boolean isAdminStateUp;
	private NetworkType networkType;
	private boolean isRouterExternal;
	private boolean isShared;
	private String id;
	private String name;

	public State getStatus() {
		return status;
	}

	public List<String> getSubnets() {
		return subnets;
	}

	public String getProviderPhyNet() {
		return providerPhyNet;
	}

	public boolean isAdminStateUp() {
		return isAdminStateUp;
	}

	public NetworkType getNetworkType() {
		return networkType;
	}

	public boolean isRouterExternal() {
		return isRouterExternal;
	}

	public boolean isShared() {
		return isShared;
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
		return "network";
	}


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
