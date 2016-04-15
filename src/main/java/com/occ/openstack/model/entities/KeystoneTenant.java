package com.occ.openstack.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.Objects;

@JsonRootName("tenant")
@JsonIgnoreProperties(ignoreUnknown = true)
public class KeystoneTenant extends CachedResource {

	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String description;
	private Boolean enabled = true;

	/**
	 * By providing an ID it is assumed this object will be mapped to an existing Tenant
	 *
	 * @return the id of the tenant
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the name of the tenant
	 */
	public String getName() {
		return name;
	}


	/**
	 * @return the description of the tenant
	 */
	public String getDescription() {
		return description;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getType() {
		return "tenant";
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public boolean isEnabled() {
		return (enabled != null && enabled);
	}

	/**
	 * Sets the enabled.
	 *
	 * @param enabled the new enabled
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return Objects.hashCode(id, name, description);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		KeystoneTenant that = KeystoneTenant.class.cast(obj);
		return Objects.equal(this.id, that.id)
			&& Objects.equal(this.name, that.name)
			&& Objects.equal(this.description, that.description);
	}

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return Objects.toStringHelper(this).omitNullValues()
			.add("id", id).add("name", name).add("description", description)
			.add("enabled", enabled)
			.toString();
	}
}
