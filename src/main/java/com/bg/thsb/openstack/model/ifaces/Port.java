package com.bg.thsb.openstack.model.ifaces;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A Network (Neutron) Port
 *
 * @author Jeremy Unruh
 */
public interface Port extends ResourceEntity {


	/**
	 * @return the current state of the port
	 */
	Network.State getState();

	/**
	 * @return the id of the network where this port is associated with
	 */
	Network getNetwork();

	/**
	 * @return the id of the device (e.g. server) using this port.
	 */
	ResourceEntity getDevice();

	/**
	 * @return the set of fixed IPs this port has been assigned
	 */
	Set<String> getFixedIps();

	Map<String, String> getAllowedAddressPairs();

	/**
	 * @return the MacAddress of this port
	 */
	String getMacAddress();

	/**
	 * @return the security group identifiers assigned to this port
	 */
	List<SecurityGroup> getSecurityGroups();


}
