package com.github.interpreter.token.type;

public class IdentifierToken implements Token {

    private String identifier;

    public IdentifierToken(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String getValue() {
        return identifier;
    }

    @Override
    public void setValue(String value) {
        this.identifier = value;
    }
}
