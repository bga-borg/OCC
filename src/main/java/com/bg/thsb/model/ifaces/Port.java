package com.bg.thsb.model.ifaces;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.common.Resource;
import org.openstack4j.model.network.builder.PortBuilder;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A Network (Neutron) Port
 *
 * @author Jeremy Unruh
 */
public interface Port extends Resource, Buildable<PortBuilder> {

	/**
	 * @return the current state of the port
	 */
	Network.State getState();

	/**
	 * @return the administrative state of port. If false, port does not forward packets.
	 */
	boolean isAdminStateUp();

	/**
	 * @return the id of the network where this port is associated with
	 */
	String getNetworkId();

	/**
	 * @return the id of the device (e.g. server) using this port.
	 */
	String getDeviceId();

	/**
	 * @return the entity (e.g.: DHCP Agent) using this port.
	 */
	String getDeviceOwner();

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
	List<String> getSecurityGroups();


}
