package com.bg.thsb.plainmodel;

import java.util.UUID;

/**
 * Volume
 */
public class Volume extends CachedResource {
    private String id;
    private String name;

    public Volume() {
    }

    public Volume(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Volume{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public static class VolumeBuilder {

        private String id;
        private String name;

        public VolumeBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public VolumeBuilder setId(String id) {
            this.id = id;
            return this;
        }

        public Volume build() {
            return new Volume(UUID.randomUUID().toString(), name);
        }
    }
}
