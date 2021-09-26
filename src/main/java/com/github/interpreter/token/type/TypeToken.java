package com.github.interpreter.token.type;

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

    public Type getType() {
        return Type.getByName(type);
    }
}
