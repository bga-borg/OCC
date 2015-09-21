/*
 * 
 */
package com.bg.thsb.openstack.model.ifaces;

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

	void setRam(int r);

	/**
	 * @return the Number of VCPUs for the flavor
	 */
	int getVcpus();

	void setVcpus(int vc);

	/**
	 * @return the size of the local disk in GB
	 */
	int getDisk();

	void setDisk(int d);

	/**
	 * @return the Swap space in MB
	 */
	int getSwap();

	void setSwap(int s);

	/**
	 * Checks if is public.
	 *
	 * @return true, if is public
	 */
	boolean isPublic();

	void setIsPublic(boolean pub);

}
