package com.bg.thsb;

import org.infinispan.notifications.Listener;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryCreated;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryModified;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryRemoved;
import org.infinispan.notifications.cachelistener.event.Event;
import org.infinispan.notifications.cachemanagerlistener.annotation.CacheStarted;
import org.infinispan.notifications.cachemanagerlistener.annotation.CacheStopped;
import org.infinispan.notifications.cachemanagerlistener.annotation.ViewChanged;

/**
 * Listen and log changes in infinispan lifecycle.
 */
@Listener
public class InfinispanEventListener {
    @CacheStarted
    public void handleStart(Event event) {
        System.out.println("Cache started");
    }


    @CacheStarted
    @CacheStopped
    @ViewChanged
    @CacheEntryCreated
    @CacheEntryModified
    @CacheEntryRemoved
    public void logEvent(Event event) {
        System.out.println(event.getCache().getName() + ": " +event.getType());
    }
}
