package com.bg.thsb.openstack.model.entities;

public class Flavor extends CachedResource{
	private String id;
	private String name;
	private int ram;
	private int vcpus;
	private int disk;
	private int swap;
	private boolean isPublic;

	public Flavor() {
	}

	public Flavor(String id, String name, int ram, int vcpus, int disk, int swap, boolean isPublic) {
		this.id = id;
		this.name = name;
		this.ram = ram;
		this.vcpus = vcpus;
		this.disk = disk;
		this.swap = swap;
		this.isPublic = isPublic;
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
		return "flavor";
	}

	public int getRam() {
		return ram;
	}

	public void setRam(int r) {
		this.ram = r;
	}

	public int getVcpus() {
		return vcpus;
	}

	public void setVcpus(int vc) {
		this.vcpus = vc;
	}

	public int getDisk() {
		return disk;
	}

	public void setDisk(int d) {
		this.disk = d;
	}

	public int getSwap() {
		return swap;
	}

	public void setSwap(int s) {
		this.swap = s;
	}

	public boolean isPublic() {
		return isPublic;
	}

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

		public Flavor createFlavorImpl() {
			return new Flavor(id, name, ram, vcpus, disk, swap, isPublic);
		}
	}
}
