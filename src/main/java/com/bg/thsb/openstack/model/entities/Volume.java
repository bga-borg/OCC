package com.bg.thsb.openstack.model.entities;

import org.openstack4j.model.storage.block.Volume.Status;

import java.util.Date;
import java.util.Map;

public class Volume extends CachedResource {
    private String id;
    private String name;
    private Status status;
    private int size;
    private Date created;
    private String imageRef;
    private Map<String, String> metaData;
    private String sourceVolid;
    private String tenantId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getType() {
        return "volume";
    }

    public Status getStatus() {
        return status;
    }

    public int getSize() {
        return size;
    }

    public Date getCreated() {
        return created;
    }

    public String getImageRef() {
        return imageRef;
    }

    public String getSourceVolid() {
        return sourceVolid;
    }

    public Map<String, String> getMetaData() {
        return metaData;
    }

}
