/*
 * 
 */
package com.bg.thsb.model.ifaces;

/**
 * An OpenStack Flavor which is a template used for configuration against running Instances
 *
 * @author Jeremy Unruh
 */
public interface Flavor extends ResourceEntity {

	/**
	 * @return the Memory in MB for the flavor
	 */
	int getRam();

	/**
	 * @return the Number of VCPUs for the flavor
	 */
	int getVcpus();

	/**
	 * @return the size of the local disk in GB
	 */
	int getDisk();

	/**
	 * @return the Swap space in MB
	 */
	int getSwap();

	/**
	 * Checks if is public.
	 *
	 * @return true, if is public
	 */
	boolean isPublic();

}
