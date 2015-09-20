package com.bg.thsb.model;

import com.bg.thsb.model.ifaces.Network;

import java.util.List;

public class NetworkImpl implements Network {
	private State status;
	private List<String> subnets;
	private String providerPhyNet;
	private boolean isAdminStateUp;
	private NetworkType networkType;
	private boolean isRouterExternal;
	private boolean isShared;

	@Override
	public State getStatus() {
		return status;
	}

	@Override
	public List<String> getSubnets() {
		return subnets;
	}

	@Override
	public String getProviderPhyNet() {
		return providerPhyNet;
	}

	@Override
	public boolean isAdminStateUp() {
		return isAdminStateUp;
	}

	@Override
	public NetworkType getNetworkType() {
		return networkType;
	}

	@Override
	public boolean isRouterExternal() {
		return isRouterExternal;
	}

	@Override
	public boolean isShared() {
		return isShared;
	}
}
