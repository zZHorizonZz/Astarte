package com.github.interpreter.token.type;

import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public enum KeyWordType {
    IF("if"),
    ELSE("else"),
    WHILE("while"),
    RETURN("return");

    private final String keyword;

    KeyWordType(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }

    @Nullable
    public static KeyWordType getByName(String value) {
        return Arrays.stream(values()).filter(keyWordType -> keyWordType.getKeyword().equals(value)).findFirst().orElse(null);
    }
}
