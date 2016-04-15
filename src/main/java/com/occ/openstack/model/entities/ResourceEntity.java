package com.occ.openstack.model.entities;


import java.io.Serializable;

/**
 * Basic serializable entity with name and id
 */
public interface ResourceEntity extends Serializable {

	String getId();

	void setId(String id);

	String getName();

	void setName(String name);

	String getType();
}