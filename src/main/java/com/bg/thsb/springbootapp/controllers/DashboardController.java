package com.bg.thsb.springbootapp.controllers;

import com.bg.thsb.infinispan.CacheWrapper;
import com.bg.thsb.springbootapp.models.DbStatus;
import com.bg.thsb.springbootapp.models.ServerInfo;
import com.bg.thsb.thesis1.EagerListTrials;
import org.infinispan.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DashboardController {
    @Autowired
    EagerListTrials eagerListTrials;

    Thread testThread = new Thread();

    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView mV = new ModelAndView();
        mV.setViewName("index");
        return mV;
    }

    @RequestMapping("/runSimpleTest")
    @ResponseBody
    public void runSimpleTest() {
        if (testThread != null && testThread.isAlive()) {
            return;
        }

        testThread = new Thread(() -> {
            try {
                eagerListTrials.testCacheMiss();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        testThread.run();
    }

    @RequestMapping("/cleanupServers")
    @ResponseBody
    public void cleanupServers(){
        eagerListTrials.cleanupServers();
    }

    @RequestMapping("/dbstatus")
    @ResponseBody
    public DbStatus getDbStatus() {
        DbStatus dbStatus = new DbStatus();

        Cache<String, Object> stringObjectCache = CacheWrapper.getCache();
        dbStatus.infinispanStatus = stringObjectCache.getStatus();
        dbStatus.listeners = stringObjectCache.getListeners();
        dbStatus.keySet = stringObjectCache.keySet();
        dbStatus.numOfElems = stringObjectCache.size();
        dbStatus.serverList.add(eagerListTrials.server1);
        dbStatus.serverList.add(eagerListTrials.server2);
        dbStatus.testThreadIsAlive = testThread.isAlive();
        for (String key : dbStatus.keySet) {
            dbStatus.content.put(key, stringObjectCache.get(key).toString());
        }

        return dbStatus;
    }

    @RequestMapping("/dbconfig")
    @ResponseBody
    public StringWrapper getDbConfig() {
        return new StringWrapper(CacheWrapper.getCache().getCacheConfiguration().toString());
    }

    public static class StringWrapper {
        public String content;
        public StringWrapper(String c) {
            content = c;
        }
    }

    @RequestMapping("/serverInfo")
    @ResponseBody
    ServerInfo getServerAddress() {
        return new ServerInfo();
    }

}
