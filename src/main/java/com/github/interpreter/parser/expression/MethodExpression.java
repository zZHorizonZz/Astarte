package com.github.interpreter.parser.expression;

public record MethodExpression(String name, Expression... arguments) implements Expression {
}
