package com.github.interpreter.token.type;

public class SeparatorToken implements Token {

    private String separator;

    public SeparatorToken(String separator) {
        this.separator = separator;
    }

    @Override
    public String getValue() {
        return separator;
    }

    @Override
    public void setValue(String value) {
        this.separator = value;
    }

    public static enum SeperatorType {

    }
}
