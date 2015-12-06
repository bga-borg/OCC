package com.bg.thsb.springbootapp.controllers;

import com.bg.thsb.eagercollection.TrialMethod;
import com.bg.thsb.infinispan.CacheWrapper;
import com.bg.thsb.springbootapp.models.DbStatus;
import com.bg.thsb.springbootapp.models.ServerInfo;
import com.bg.thsb.thesis1.EagerListTrials;
import com.google.common.collect.Lists;
import org.infinispan.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

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

    @RequestMapping(value = "/trialRunner", params = "testName", method = RequestMethod.POST)
    @ResponseBody
    public void trialRunner(@RequestParam("testName") String testName) {
        if (testThread != null && testThread.isAlive()) {
            return;
        }

        testThread = new Thread(() -> {
            try {
                Method testMethod = eagerListTrials.getClass().getMethod(testName);
                testMethod.invoke(eagerListTrials);
            } catch (Exception e) { // !!!!!!POKEMON!!!!!!!!! xD
                e.printStackTrace();
            }
        });

        testThread.run();
    }

    @RequestMapping("/cleanupServers")
    @ResponseBody
    public void cleanupServers() {
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

    @RequestMapping("/getTrials")
    @ResponseBody
    List<String> getTrials() {
        List<String> methodStringList = Lists.newArrayList();
        Method[] methods = eagerListTrials.getClass().getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(TrialMethod.class)) {
                methodStringList.add(method.getName());
            }
        }
        return methodStringList;
    }
}
