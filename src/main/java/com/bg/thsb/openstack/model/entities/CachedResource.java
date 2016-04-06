package com.bg.thsb.openstack.model.entities;


public abstract class CachedResource implements ResourceEntity {
    /**
     * True means the resource is only referenced,
     * and can not be deleted or modified by this updater
     */
    protected boolean onlyReference = false;

    public boolean isOnlyReference() {
        return onlyReference;
    }

    public void setOnlyReference(boolean onlyReference) {
        this.onlyReference = onlyReference;
    }
}