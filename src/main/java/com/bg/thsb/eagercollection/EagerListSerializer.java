package com.bg.thsb.eagercollection;

import com.bg.thsb.plainmodel.ResourceEntity;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class EagerListSerializer extends JsonSerializer<EagerList> {
    @Override
    public void serialize(EagerList value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeStartArray();
        for (int i = 0; i < value.size(); i++) {
            jgen.writeStartObject();
            ResourceEntity resourceEntity = (ResourceEntity) value.get(i);
            jgen.writeStringField("id", resourceEntity.getId());
            jgen.writeStringField("name", resourceEntity.getName());
            jgen.writeEndObject();
        }
        jgen.writeEndArray();
    }
}
