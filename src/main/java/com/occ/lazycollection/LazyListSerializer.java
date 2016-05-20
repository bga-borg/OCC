package com.occ.lazycollection;

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

    public enum SerializerType {
        LAZY,
        EAGER
    }
}
