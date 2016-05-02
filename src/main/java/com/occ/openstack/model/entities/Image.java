package com.occ.openstack.model.entities;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.openstack4j.model.compute.Image.Status;

import java.util.Date;
import java.util.Map;

@Indexed
public class Image extends CachedResource {
    @Field
    private String id;
    @Field
    private String name;
    @Field
    private long size;
    @Field
    private int minDisk;
    @Field
    private int minRam;
    @Field
    private Status status;
    @Field
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

    public void setSize(long size) {
        this.size = size;
    }

    public void setMinDisk(int minDisk) {
        this.minDisk = minDisk;
    }

    public void setMinRam(int minRam) {
        this.minRam = minRam;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setMetaData(Map<String, Object> metaData) {
        this.metaData = metaData;
    }

    public boolean isSnapsot() {
        return isSnapsot;
    }

    public void setSnapsot(boolean snapsot) {
        isSnapsot = snapsot;
    }
}
