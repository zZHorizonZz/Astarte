package com.github.interpreter.token.type;

import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public enum KeyWord {
    IF("if"),
    ELSE("else"),
    WHILE("while"),
    RETURN("return");

    private final String keyword;

    KeyWord(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }

    @Nullable
    public static KeyWord getByName(String value) {
        return Arrays.stream(values()).filter(keyWord -> keyWord.getKeyword().equals(value)).findFirst().orElse(null);
    }
}
