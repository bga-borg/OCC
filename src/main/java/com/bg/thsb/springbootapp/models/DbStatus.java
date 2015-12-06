package com.bg.thsb.springbootapp.models;

import com.bg.thsb.plainmodel.Server;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.infinispan.commons.util.CloseableIteratorSet;
import org.infinispan.lifecycle.ComponentStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class DbStatus {
    public int numOfElems;
    public HashMap<String, String> content = Maps.newHashMap();
    public HashMap<String, Integer> lifeSpan = Maps.newHashMap();

    public ComponentStatus infinispanStatus;
    public Set<Object> listeners;
    public CloseableIteratorSet<String> keySet;
    public List<Server> serverList = Lists.newArrayList();
    public boolean testThreadIsAlive;
}
