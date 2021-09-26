package com.github.interpreter.token.type;

public class LiteralToken extends Token {

    private String literal;

    public LiteralToken(String literal) {
        this.literal = literal;
    }

    @Override
    public String getValue() {
        return literal;
    }

    @Override
    public void setValue(String value) {
        this.literal = value;
    }
}
