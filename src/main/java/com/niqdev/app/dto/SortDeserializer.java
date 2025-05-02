package com.niqdev.app.dto;

import java.io.IOException;

import org.springframework.data.domain.Sort;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class SortDeserializer extends JsonDeserializer<Sort> {
    @Override
    public Sort deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getValueAsString(); // e.g. "createdAt: DESC"
        if (value == null) return Sort.unsorted();

        String[] parts = value.split(":");
        if (parts.length != 2) return Sort.unsorted();

        String property = parts[0].trim();
        String direction = parts[1].trim().toUpperCase();

        return Sort.by(Sort.Direction.fromString(direction), property);
    }
}
