package com.bg.thsb.model;

import com.bg.thsb.model.ifaces.Flavor;

public class FlavorImpl implements Flavor {
	private String id;
	private String name;
	private int ram;
	private int vcpus;
	private int disk;
	private int swap;
	private boolean isPublic;

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int getRam() {
		return ram;
	}

	@Override
	public int getVcpus() {
		return vcpus;
	}

	@Override
	public int getDisk() {
		return disk;
	}

	@Override
	public int getSwap() {
		return swap;
	}

	@Override
	public boolean isPublic() {
		return isPublic;
	}
}
