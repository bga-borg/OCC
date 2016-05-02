package com.occ.openstack;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
        return Boolean.parseBoolean(properties.getProperty("occ.cu.work_offline"));
    }

    public String getCacheImportFilename() {
        return properties.getProperty("occ.cu.cache_import_filename");
    }

    public int getExportStatusToJSONInterval() {
        return Integer.parseInt(properties.getProperty("occ.cu.export_status_to_json_interval"));
    }

    public int getCacheSerializationInterval() {
        return Integer.parseInt((properties.getProperty("occ.cu.cache_serialization_interval")));
    }

    public String[] getListOfActiveCacheUpdaters() {
        return properties.getProperty("occ.cu.active_cache_updaters").split(",");
    }

    public String getActiveCacheUpdatersPackage() {
        return properties.getProperty("occ.cu.active_cache_updaters_package");
    }

    public Boolean isInstanceSynchronizerEnabled() {
        return Boolean.parseBoolean(properties.getProperty("occ.dal.enable_instance_synchronizer"));
    }

    public Boolean isDalStreamParallel() {
        return Boolean.parseBoolean(properties.getProperty("occ.dal.STREAM_PARALLEL"));
    }

    public int getDalWeakPutLifetimeSec() {
        return Integer.parseInt(properties.getProperty("occ.dal.weak_put_lifetime_sec"));
    }
}