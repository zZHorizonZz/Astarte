package com.github.interpreter.parser.expression;

public record MethodReferenceExpression(String name, Expression... arguments) implements Expression {
}
