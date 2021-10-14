package com.github.interpreter.parser.expression;

import com.github.interpreter.language.Object;

public record VariableExpression(Object value) implements Expression {

}
