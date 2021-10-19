package com.github.interpreter.parser.expression;

public record OperatorExpression(
        Expression leftSide,
        Expression rightSide,
        String operator) implements Expression {
}
