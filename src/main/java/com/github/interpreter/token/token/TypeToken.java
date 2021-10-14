package com.github.interpreter.token.token;

import com.github.interpreter.token.type.GenericType;
import com.github.interpreter.token.type.TokenType;

public class TypeToken extends Token {

    private String type;

    public TypeToken(String type) {
        super(TokenType.TYPE);
        this.type = type;
    }

    @Override
    public String getValue() {
        return type;
    }

    @Override
    public void setValue(String type) {
        this.type = type;
    }

    public GenericType getType() {
        return GenericType.getByName(type);
    }
}
