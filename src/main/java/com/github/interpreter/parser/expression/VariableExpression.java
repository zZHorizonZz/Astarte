package com.github.interpreter.parser.expression;

public class VariableExpression implements Expression {

    private final String value;

    public VariableExpression(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
