package com.fabiano.enums.converters;

import com.fabiano.enums.StatusEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class StatusEnumConverter implements AttributeConverter<StatusEnum,String> {
    @Override
    public String convertToDatabaseColumn(StatusEnum statusEnum) {
        if(statusEnum==null){
            return null;
        }
        return statusEnum.getValue();
    }

    @Override
    public StatusEnum convertToEntityAttribute(String value) {
        if(value==null){
            return null;
        }
        return Stream.of(StatusEnum.values())
                .filter(c->c.getValue().equals(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
