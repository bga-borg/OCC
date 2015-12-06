package com.bg.thsb.plainmodel;

import com.bg.thsb.eagercollection.EagerList;
import com.bg.thsb.eagercollection.EagerListSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;
import java.util.UUID;

/**
 * Server
 */
public class Server extends CachedResource {
    private String id;
    private String name;

    private Image image;

    @JsonSerialize(using = EagerListSerializer.class)
    private EagerList<Volume> volumes;

    public Server() {
        volumes = new EagerList<>();
    }

    public Server(String id, String name, Image image, EagerList<Volume> volumes) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.volumes = volumes;
    }

    public List<Volume> getVolumes() {
        return volumes;
    }

    public void setVolumes(EagerList<Volume> volumes) {
        this.volumes = volumes;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
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

    public static class ServerBuilder {

        private String id;
        private String name;
        private Image image;
        private EagerList<Volume> volumes;

        public ServerBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ServerBuilder setId(String id) {
            this.id = id;
            return this;
        }

        public ServerBuilder setImage(Image image) {
            this.image = image;
            return this;
        }

        public ServerBuilder setVolumes(EagerList<Volume> volumes) {
            this.volumes = volumes;
            return this;
        }

        public ServerBuilder setVolumes(List<Volume> volumes) {
            EagerList<Volume> eagerList = new EagerList<>();
            for (Volume volume : volumes) {
                eagerList.add(volume);
            }
            this.setVolumes(eagerList);
            return this;
        }

        public Server build() {
            if (id == null)
                id = UUID.randomUUID().toString();

            if (volumes == null) {
                volumes = new EagerList<>();
            }
            return new Server(id, name, image, volumes);
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
