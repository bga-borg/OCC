package com.bg.thsb.model.ifaces;

/**
 * The Interface SecurityGroupRule.
 *
 *
 * @author Nathan Anderson
 */
public interface SecurityGroupRule extends ResourceEntity {

	/**
	 * Gets the port range max.
	 *
	 * @return the port range max
	 */
	Integer getPortRangeMax();

	/**
	 * Gets the port range min.
	 *
	 * @return the port range min
	 */
	Integer getPortRangeMin();

	/**
	 * Gets the protocol.
	 *
	 * @return the protocol
	 */
	String getProtocol();

	/**
	 * Gets the remote ip prefix.
	 *
	 * @return the remote ip prefix
	 */
	String getRemoteIpPrefix();
}
