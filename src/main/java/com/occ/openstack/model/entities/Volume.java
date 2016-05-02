package com.occ.openstack.model.entities;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.openstack4j.model.storage.block.Volume.Status;

import java.util.Date;
import java.util.Map;

@Indexed
public class Volume extends CachedResource {
    @Field
    private String id;
    @Field
    private String name;
    @Field
    private Status status;
    @Field
    private int size;
    @Field
    private Date created;
    @Field
    private String imageRef;
    private Map<String, String> metaData;
    @Field
    private String sourceVolid;
    @Field
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

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setImageRef(String imageRef) {
        this.imageRef = imageRef;
    }

    public void setMetaData(Map<String, String> metaData) {
        this.metaData = metaData;
    }

    public void setSourceVolid(String sourceVolid) {
        this.sourceVolid = sourceVolid;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}
