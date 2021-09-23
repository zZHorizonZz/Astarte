package com.github.interpreter.token.type;

public class OperatorToken implements Token {

    private String operator;

    public OperatorToken(String operator) {
        this.operator = operator;
    }

    @Override
    public String getValue() {
        return operator;
    }

    @Override
    public void setValue(String value) {
        this.operator = value;
    }
}
