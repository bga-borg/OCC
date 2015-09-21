package com.bg.thsb.openstack.model.ifaces;

/**
 * The Interface FloatingIP.
 *
 *
 * @author nanderson
 */
public interface FloatingIP extends ResourceEntity {

	/**
	 * Gets the instance id.
	 *
	 * @return the instance id
	 */
	String getInstanceId();

	/**
	 * Gets the floating ip address.
	 *
	 * @return the floating ip address
	 */
	String getFloatingIpAddress();

	/**
	 * Gets the fixed ip address.
	 *
	 * @return the fixed ip address
	 */
	String getFixedIpAddress();

	/**
	 * Gets the pool.
	 *
	 * @return the pool name
	 */
	String getPool();
}