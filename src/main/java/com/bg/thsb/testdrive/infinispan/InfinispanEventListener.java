package com.bg.thsb.testdrive.infinispan;


import org.infinispan.notifications.Listener;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryCreated;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryModified;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryRemoved;
import org.infinispan.notifications.cachelistener.event.Event;
import org.infinispan.notifications.cachemanagerlistener.annotation.CacheStarted;
import org.infinispan.notifications.cachemanagerlistener.annotation.CacheStopped;
import org.infinispan.notifications.cachemanagerlistener.annotation.ViewChanged;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Listen and log changes in infinispan lifecycle.
 */
@Listener
public class InfinispanEventListener {
    Logger logger = LoggerFactory.getLogger(InfinispanEventListener.class);

    @CacheStarted
    @CacheStopped
    public void handleStart(Event event) {
        logger.info(event.getCache().getName() + ": " + event.getType());
    }


    @ViewChanged
    @CacheEntryCreated
    @CacheEntryModified
    @CacheEntryRemoved
    public void logEvent(Event event) {
        logger.info(event.getCache().getName() + ": " +event.getType());
    }
}
