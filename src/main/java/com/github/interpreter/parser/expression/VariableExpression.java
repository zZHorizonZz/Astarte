package com.github.interpreter.parser.expression;

public record VariableExpression(String value) implements Expression {

    public String getValue() {
        return value;
    }
}
