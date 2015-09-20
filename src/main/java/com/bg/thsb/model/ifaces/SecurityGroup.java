package com.bg.thsb.model.ifaces;

import java.util.List;

/**
 * The Interface SecurityGroup.
 *
 * @author Nathan Anderson
 */
public interface SecurityGroup extends ResourceEntity {

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	String getDescription();

	/**
	 * Gets the rules.
	 *
	 * @return the rules
	 */
	List<? extends SecurityGroupRule> getRules();

}
