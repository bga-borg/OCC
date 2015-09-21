package com.bg.thsb.openstack.model.ifaces;

import java.io.Serializable;

/**
 * Basic serializable entity with name and id
 */
public interface ResourceEntity extends Serializable {

	String getId();

	void setId(String id);

	String getName();

	void setName(String name);
}
