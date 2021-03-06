package com.github.interpreter.token.token;

import com.github.interpreter.token.type.TokenType;

public class SeparatorToken extends Token {

    private String separator;

    public SeparatorToken(String separator) {
        super(TokenType.SEPARATOR);
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
