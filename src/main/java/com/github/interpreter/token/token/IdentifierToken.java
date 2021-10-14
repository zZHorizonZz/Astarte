package com.github.interpreter.token.token;

import com.github.interpreter.token.type.TokenType;

public class IdentifierToken extends Token {

    private String identifier;

    public IdentifierToken(String identifier) {
        super(TokenType.IDENTIFIER);
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
