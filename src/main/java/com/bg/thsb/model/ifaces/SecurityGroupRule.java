package com.bg.thsb.model.ifaces;

/**
 * The Interface SecurityGroupRule.
 *
 *
 * @author Nathan Anderson
 */
public interface SecurityGroupRule extends ResourceEntity {

	/**
	 * Gets the direction.
	 *
	 * @return the direction
	 */
	String getDirection();

	/**
	 * Gets the ether type.
	 *
	 * @return the ether type
	 */
	String getEtherType();

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
	 * Gets the remote group id.
	 *
	 * @return the remote group id
	 */
	String getRemoteGroupId();

	/**
	 * Gets the remote ip prefix.
	 *
	 * @return the remote ip prefix
	 */
	String getRemoteIpPrefix();

	/**
	 * Gets the security group id.
	 *
	 * @return the security group id
	 */
	String getSecurityGroupId();

	/**
	 * Gets the tenant id.
	 *
	 * @return the tenant id
	 */
	String getTenantId();


}
