package com.loiane.enums.converter;

import com.loiane.enums.Status;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Objects;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, String> {

    @Override
    public String convertToDatabaseColumn(final Status status) {
        if (Objects.isNull(status)) return null;

        return status.getValue();
    }

    @Override
    public Status convertToEntityAttribute(final String value) {
        if (Objects.isNull(value)) return null;

        return Stream.of(Status.values())
                .filter(c -> c.getValue().equals(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
