package com.bg.thsb.openstack.model.impl;

import com.bg.thsb.openstack.model.ifaces.Flavor;

public class FlavorImpl implements Flavor {
	private String id;
	private String name;
	private int ram;
	private int vcpus;
	private int disk;
	private int swap;
	private boolean isPublic;

	public FlavorImpl() {
	}

	public FlavorImpl(String id, String name, int ram, int vcpus, int disk, int swap, boolean isPublic) {
		this.id = id;
		this.name = name;
		this.ram = ram;
		this.vcpus = vcpus;
		this.disk = disk;
		this.swap = swap;
		this.isPublic = isPublic;
	}

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
	public void setRam(int r) {
		this.ram = r;
	}

	@Override
	public int getVcpus() {
		return vcpus;
	}

	@Override
	public void setVcpus(int vc) {
		this.vcpus = vc;
	}

	@Override
	public int getDisk() {
		return disk;
	}

	@Override
	public void setDisk(int d) {
		this.disk = d;
	}

	@Override
	public int getSwap() {
		return swap;
	}

	@Override
	public void setSwap(int s) {
		this.swap = s;
	}

	@Override
	public boolean isPublic() {
		return isPublic;
	}

	@Override
	public void setIsPublic(boolean pub) {
		this.isPublic = pub;
	}

	public static class FlavorBuilder {

		private String id;
		private String name;
		private int ram;
		private int vcpus;
		private int disk;
		private int swap;
		private boolean isPublic;

		public FlavorBuilder setId(String id) {
			this.id = id;
			return this;
		}

		public FlavorBuilder setName(String name) {
			this.name = name;
			return this;
		}

		public FlavorBuilder setRam(int ram) {
			this.ram = ram;
			return this;
		}

		public FlavorBuilder setVcpus(int vcpus) {
			this.vcpus = vcpus;
			return this;
		}

		public FlavorBuilder setDisk(int disk) {
			this.disk = disk;
			return this;
		}

		public FlavorBuilder setSwap(int swap) {
			this.swap = swap;
			return this;
		}

		public FlavorBuilder setIsPublic(boolean isPublic) {
			this.isPublic = isPublic;
			return this;
		}

		public FlavorImpl createFlavorImpl() {
			return new FlavorImpl(id, name, ram, vcpus, disk, swap, isPublic);
		}
	}
}
