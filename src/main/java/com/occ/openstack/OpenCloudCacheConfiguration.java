package com.occ.openstack;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class OpenCloudCacheConfiguration {

    InputStream inputStream = null;
    Properties properties = new Properties();

    public OpenCloudCacheConfiguration() {
        try {
            inputStream = this.getClass().getClassLoader().getResourceAsStream("occ.properties");
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
        return Boolean.parseBoolean(properties.getProperty("occ.work_offline"));
    }

    public String getCacheImportFilename(){
        return properties.getProperty("occ.cache_import_filename");
    }

    public int getExportStatusToJSONInterval() { return Integer.parseInt(properties.getProperty("occ.export_status_to_json_interval")); }

    public int getCacheSerializationInterval() {
        return Integer.parseInt((properties.getProperty("occ.cache_serialization_interval")));
    }

    public String[] getListOfActiveCacheUpdaters(){
        return properties.getProperty("occ.active_cache_updaters").split(",");
    }

    public String getActiveCacheUpdatersPackage(){
        return properties.getProperty("occ.active_cache_updaters_package");
    }

    public Boolean isInstanceSynchronizerEnabled(){
        return Boolean.parseBoolean(properties.getProperty("occ.enable_instance_synchronizer"));
    }
}