package com.bg.thsb.openstack.model.entities;

import org.openstack4j.model.network.NetworkType;
import org.openstack4j.model.network.State;

import java.util.List;

public class Network extends CachedResource {
    private State status;
    private List<String> subnets;
    private String providerPhyNet;
    private boolean isAdminStateUp;
    private NetworkType networkType;
    private boolean isRouterExternal;
    private boolean isShared;
    private String id;
    private String name;
    private String tenantId;

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

}
