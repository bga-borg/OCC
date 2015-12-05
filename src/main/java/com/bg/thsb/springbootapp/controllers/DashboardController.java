package com.bg.thsb.springbootapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Controller
public class DashboardController {

    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView mV = new ModelAndView();

        mV.setViewName("index");

        return mV;
    }

    @RequestMapping("/serverInfo")
    @ResponseBody
    ServerInfo getServerAddress() {
        return new ServerInfo();
    }

    public static class ServerInfo {
        public String serverIp;

        ServerInfo() {
            this.serverIp = "not defined";
            InetAddress[] allMyIps = null;
            try {
                InetAddress localhost = InetAddress.getLocalHost();
                allMyIps = InetAddress.getAllByName(localhost.getCanonicalHostName());
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            if (allMyIps != null && allMyIps.length > 1) {
                this.serverIp = "";
                for (InetAddress ip : allMyIps) {
                    this.serverIp += ip.getHostAddress() + "; ";
                }
            }
        }
    }
}
