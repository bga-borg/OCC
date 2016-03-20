package com.bg.thsb.openstack;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class OpenStackConfiguration {

    InputStream inputStream = null;
    Properties properties = new Properties();

    public OpenStackConfiguration() {
        try {
            inputStream = this.getClass().getClassLoader().getResourceAsStream("openstack.properties");
            properties.load(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getEndpoint() {
        return properties.getProperty("openstack.endpoint");
    }

    public String getUser() {
        return properties.getProperty("openstack.user");
    }

    public String getUserAdmin() {
        return properties.getProperty("openstack.useradmin");
    }

    public String getUserPass() {
        return properties.getProperty("openstack.pass");
    }

    public String getTenant() {
        return properties.getProperty("openstack.tenant");
    }

    public boolean isOffline() {
        return Boolean.parseBoolean(properties.getProperty("work_offline"));
    }

    public String getCacheImportFilename(){
        return properties.getProperty("cache_import_filename");
    }


}