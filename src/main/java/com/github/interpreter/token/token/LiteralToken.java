package com.github.interpreter.token.token;

import com.github.interpreter.token.type.TokenType;

public class LiteralToken extends Token {

    private String literal;

    public LiteralToken(String literal) {
        super(TokenType.LITERAL);
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
