package com.bg.thsb.lazycollection;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class LazyListSerializer extends JsonSerializer<LazyList> {
    public static SerializerType serializerType = SerializerType.LAZY;

    @Override
    public void serialize(LazyList lazyList, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        switch (serializerType) {
            case LAZY:
                lazyIdSerializer(lazyList, jgen, provider);
                break;
            case EAGER:
                /*eagerEntitySerializer(lazyList, jgen, provider);*/
                break;
            default:
                throw new RuntimeException("Wrong serializer type");
        }
    }

    public void lazyIdSerializer(LazyList lazyList, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeStartArray();
        for (int i = 0; i < lazyList.getStoredKeys().size(); i++) {
            jgen.writeStartObject();
            jgen.writeStringField("id", (String) lazyList.getStoredKeys().get(i));
            jgen.writeEndObject();
        }
        jgen.writeEndArray();
    }

    /**
     * Ha rosszul kezeled a writeStartEntity/writeEndEntity implement�ci�kat
     * akkor igen egzotikus hib�kat dob�l ez a cuccos
     *
     * @throws IOException
     * <p>
     * public void eagerEntitySerializer(LazyList lazyList, JsonGenerator jgen, SerializerProvider provider) throws IOException {
     * jgen.writeStartArray();
     * for (int i = 0; i < lazyList.size(); i++) {
     * ResourceEntity resourceEntity = (ResourceEntity) lazyList.get(i);
     * if(resourceEntity == null){
     * continue;
     * }
     * jgen.writeStartObject();
     * jgen.writeStringField("id", resourceEntity.getId());
     * jgen.writeStringField("name", resourceEntity.getName());
     * jgen.writeEndObject();
     * }
     * jgen.writeEndArray();
     * }
     */
    public enum SerializerType {
        LAZY,
        EAGER
    }
}
