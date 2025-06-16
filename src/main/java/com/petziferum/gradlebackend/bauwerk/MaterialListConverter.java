package com.petziferum.gradlebackend.bauwerk;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class MaterialListConverter implements AttributeConverter<List<Materialart>, String> {

    @Override
    public String convertToDatabaseColumn(List<Materialart> attribute) {
        if (attribute == null || attribute.isEmpty()) return "";
        return attribute.stream()
                .map(Materialart::name)
                .collect(Collectors.joining(","));
    }

    @Override
    public List<Materialart> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) return new ArrayList<>();
        return Arrays.stream(dbData.split(","))
                .map(String::trim)
                .map(Materialart::valueOf)
                .collect(Collectors.toList());
    }
}

