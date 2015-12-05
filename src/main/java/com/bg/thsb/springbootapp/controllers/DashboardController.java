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

    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView mV = new ModelAndView();

        Cache<String, Object> stringObjectCache = CacheWrapper.getCache();
        stringObjectCache.getStatus();

        mV.setViewName("index");
        return mV;
    }

    @RequestMapping("/runSimpleTest")
    @ResponseBody
    public void runSimpleTest() {
        new Thread(() -> {
            try {
                eagerListTrials.testCacheMiss();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).run();
    }

    @RequestMapping("/dbstatus")
    @ResponseBody
    public DbStatus getDbStatus() {
        DbStatus dbStatus = new DbStatus();

        Cache<String, Object> stringObjectCache = CacheWrapper.getCache();
        dbStatus.status = stringObjectCache.getStatus();
        dbStatus.listeners = stringObjectCache.getListeners();
        dbStatus.keySet = stringObjectCache.keySet();
        dbStatus.numOfElems = stringObjectCache.size();
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
