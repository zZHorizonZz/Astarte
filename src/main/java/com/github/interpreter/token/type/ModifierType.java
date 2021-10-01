package com.github.interpreter.token.type;

import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public enum ModifierType {
    FINAL("final"),
    SYSTEM("system");

    private final String modifier;

    ModifierType(String keyword) {
        this.modifier = keyword;
    }

    public String getModifier() {
        return modifier;
    }

    @Nullable
    public static ModifierType getByName(String value) {
        return Arrays.stream(values()).filter(keyWord -> keyWord.getModifier().equals(value)).findFirst().orElse(null);
    }
}
