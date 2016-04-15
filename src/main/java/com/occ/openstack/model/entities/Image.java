package com.occ.openstack.model.entities;

import org.openstack4j.model.compute.Image.Status;

import java.util.Date;
import java.util.Map;

public class Image extends CachedResource {
    private String id;
    private String name;
    private long size;
    private int minDisk;
    private int minRam;
    private Status status;
    private Date created;
    private Map<String, Object> metaData;
    private boolean isSnapsot;

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
        return "image";
    }

    public long getSize() {
        return size;
    }

    public int getMinDisk() {
        return minDisk;
    }

    public int getMinRam() {
        return minRam;
    }

    public Status getStatus() {
        return status;
    }

    public Date getCreated() {
        return created;
    }

    public Map<String, Object> getMetaData() {
        return metaData;
    }

    public boolean isSnapshot() {
        return isSnapsot;
    }

}
