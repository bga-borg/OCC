package com.bg.thsb.springbootapp.models;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by bg on 2015. 12. 05..
 */
public class ServerInfo {
    public String serverIp;

    public ServerInfo() {
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
