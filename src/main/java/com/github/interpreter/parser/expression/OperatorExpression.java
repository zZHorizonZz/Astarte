package com.github.interpreter.parser.expression;

public record OperatorExpression(
        Expression rightSide,
        Expression leftSide,
        String operator) implements Expression {
}
