package com.bg.thsb.openstack.model.impl;

import com.bg.thsb.openstack.model.ifaces.*;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class ServerImpl implements Server {
	private String id;
	private String name;
	private Status status;
	private Map<String, String> metaData;
	private List<SecurityGroup> securityGroups;
	private Date launchedAt;
	private Date terminatedAt;
	private Port port;
	private Volume volume;
	private Image image;
	private Flavor flavor;

	public ServerImpl() {
		this.securityGroups = Lists.newArrayList();
		this.metaData = Maps.newHashMap();
	}

	private ServerImpl(String id, String name, Status status,
		Date launchedAt, Date terminatedAt, Port port, Volume volume, Image image,
		Flavor flavor) {
		this();
		this.id = id;
		this.name = name;
		this.status = status;
		this.launchedAt = launchedAt;
		this.terminatedAt = terminatedAt;
		this.port = port;
		this.volume = volume;
		this.image = image;
		this.flavor = flavor;
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
	public Port getPort() {
		return port;
	}

	@Override
	public void setPort(Port p) {
		this.port = p;
	}

	@Override
	public Volume getVolume() {
		return volume;
	}

	@Override
	public void setVolume(Volume v) {
		this.volume = v;
	}

	@Override
	public Image getImage() {
		return image;
	}

	@Override
	public void setImage(Image i) {
		this.image = i;
	}

	@Override
	public Flavor getFlavor() {
		return flavor;
	}

	@Override
	public void setFlavor(Flavor f) {
		this.flavor = f;
	}

	@Override
	public Status getStatus() {
		return status;
	}

	@Override
	public void setStatus(Status s) {
		this.status = s;
	}

	@Override
	public Map<String, String> getMetadata() {
		return metaData;
	}

	@Override
	public List<SecurityGroup> getSecurityGroups() {
		return this.securityGroups;
	}

	@Override
	public Date getLaunchedAt() {
		return launchedAt;
	}

	@Override
	public void setLauchedAt(Date lA) {
		this.launchedAt = lA;
	}

	@Override
	public Date getTerminatedAt() {
		return terminatedAt;
	}

	@Override
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
		private Image image;
		private Flavor flavor;

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

		public ServerBuilder setImage(Image image) {
			this.image = image;
			return this;
		}

		public ServerBuilder setFlavor(Flavor flavor) {
			this.flavor = flavor;
			return this;
		}

		public ServerImpl build() {
			return new ServerImpl(id, name, status, launchedAt, terminatedAt, port, volume, image, flavor);
		}
	}
}
