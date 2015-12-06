package com.bg.thsb.eagercollection;

import com.bg.thsb.plainmodel.ResourceEntity;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class EagerListSerializer extends JsonSerializer<EagerList> {
    public static SerializerType serializerType = SerializerType.EAGER;

    @Override
    public void serialize(EagerList eagerList, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        switch (serializerType) {
            case LAZY:
                lazyIdSerializer(eagerList, jgen, provider);
                break;
            case EAGER:
                eagerEntitySerializer(eagerList, jgen, provider);
                break;
            default:
                throw new RuntimeException("Wrong serializer type");
        }
    }

    public void lazyIdSerializer(EagerList eagerList, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeStartArray();
        for (int i = 0; i < eagerList.getStoredKeys().size(); i++) {
            jgen.writeStartObject();
            jgen.writeStringField("id", (String) eagerList.getStoredKeys().get(i));
            jgen.writeEndObject();
        }
        jgen.writeEndArray();
    }

    /**
     * Ha rosszul kezeled a writeStartEntity/writeEndEntity implementációkat
     * akkor igen egzotikus hibákat dobál ez a cuccos
     *
     * @param eagerList
     * @param jgen
     * @param provider
     * @throws IOException
     */
    public void eagerEntitySerializer(EagerList eagerList, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeStartArray();
        for (int i = 0; i < eagerList.size(); i++) {
            ResourceEntity resourceEntity = (ResourceEntity) eagerList.get(i);
            if(resourceEntity == null){
                continue;
            }
            jgen.writeStartObject();
            jgen.writeStringField("id", resourceEntity.getId());
            jgen.writeStringField("name", resourceEntity.getName());
            jgen.writeEndObject();
        }
        jgen.writeEndArray();
    }

    public enum SerializerType {
        LAZY,
        EAGER
    }
}
