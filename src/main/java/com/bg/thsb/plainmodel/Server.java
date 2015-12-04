package com.bg.thsb.plainmodel;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Server
 */
public class Server extends CachedResource {
    private String id;
    private String name;

    private Image image;
    private List<Volume> volumes;

    public Server() {
        volumes = Lists.newArrayList();
    }

    public Server(String id, String name, Image image, List<Volume> volumes) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.volumes = volumes;
    }

    public List<Volume> getVolumes() {
        return volumes;
    }

    public void setVolumes(List<Volume> volumes) {
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
        private List<Volume> volumes = new ArrayList<>();

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

        public ServerBuilder setVolumes(List<Volume> volumes) {
            this.volumes = volumes;
            return this;
        }

        public Server build() {
            if (id == null)
                id = UUID.randomUUID().toString();

            if (volumes == null) {
                volumes = Lists.newArrayList();
            }
            return new Server(id, name, image, volumes);
        }
    }
}
