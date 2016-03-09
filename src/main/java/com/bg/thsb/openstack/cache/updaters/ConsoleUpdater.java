package com.bg.thsb.openstack.cache.updaters;

import org.springframework.stereotype.Service;

/**
 * ConsoleUpdater
 *
 */
@Service
public class ConsoleUpdater extends CacheUpdater {
	@Override
	public void run() {
		System.out.println("Hello from consoleUpdater");
	}
}
