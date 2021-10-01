package com.github.interpreter.token.token;

import com.github.interpreter.token.type.TokenType;

public class OperatorToken extends Token {

    private String operator;

    public OperatorToken(String operator) {
        super(TokenType.OPERATOR);
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
