package com.bg.thsb.springbootapp.controllers;

import com.bg.thsb.infinispan.InfinispanCacheWrapper;
import com.bg.thsb.springbootapp.models.DbStatus;
import com.bg.thsb.springbootapp.models.ServerInfo;
import com.google.common.collect.Lists;
import org.infinispan.Cache;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
public class DashboardController {

    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView mV = new ModelAndView();
        mV.setViewName("index");
        return mV;
    }

    Cache<String, Object> stringObjectCache;

    @PostConstruct
    public void init() {
        stringObjectCache = InfinispanCacheWrapper.getCache();
    }


    @RequestMapping("/dbstatus")
    @ResponseBody
    public DbStatus getDbStatus() {
        DbStatus dbStatus = new DbStatus();

        dbStatus.infinispanStatus = stringObjectCache.getStatus();
        dbStatus.listeners = stringObjectCache.getListeners();
        dbStatus.keySet = stringObjectCache.keySet();
        dbStatus.numOfElems = stringObjectCache.size();

        for (String key : dbStatus.keySet) {
            dbStatus.content.put(key, stringObjectCache.get(key));
        }

        return dbStatus;
    }

    @RequestMapping("/dbconfig")
    @ResponseBody
    public StringWrapper getDbConfig() {
        return new StringWrapper(stringObjectCache.getCacheConfiguration().toString());
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
