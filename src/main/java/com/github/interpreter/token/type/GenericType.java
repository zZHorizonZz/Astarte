package com.github.interpreter.token.type;

import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public enum GenericType {
    BYTE("byte"),
    SHORT("short"),
    INTEGER("int"),
    LONG("long"),
    FLOAT("float"),
    DOUBLE("double"),
    BOOLEAN("boolean"),
    STRING("string"),
    CLASS("class");

    private final String type;

    GenericType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Nullable
    public static GenericType getByName(String value) {
        return Arrays.stream(values()).filter(type -> type.getType().equals(value)).findFirst().orElse(null);
    }
}
