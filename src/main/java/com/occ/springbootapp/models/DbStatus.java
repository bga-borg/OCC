package com.occ.springbootapp.models;

import com.google.common.collect.Maps;
import org.infinispan.commons.util.CloseableIteratorSet;
import org.infinispan.lifecycle.ComponentStatus;

import java.util.HashMap;
import java.util.Set;

public class DbStatus {
    public int numOfElems;
    public HashMap<String, Object> content = Maps.newHashMap();
    public ComponentStatus infinispanStatus;
    public Set<Object> listeners;
    public CloseableIteratorSet<String> keySet;
}
