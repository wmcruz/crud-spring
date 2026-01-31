package com.loiane.enums.converter;

import com.loiane.enums.Category;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Objects;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class CategoryConverter implements AttributeConverter<Category, String> {

    @Override
    public String convertToDatabaseColumn(final Category category) {
        if (Objects.isNull(category)) return null;

        return category.getValue();
    }

    @Override
    public Category convertToEntityAttribute(final String value) {
        if (Objects.isNull(value)) return null;

        return Stream.of(Category.values())
                .filter(c -> c.getValue().equals(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
