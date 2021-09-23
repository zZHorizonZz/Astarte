package com.github.interpreter.parser.expression;

public class OperatorExpression implements Expression {

    private final String expression;

    private final Expression rightSide;
    private final Expression leftSide;

    public OperatorExpression(Expression rightSide, Expression leftSide, String expression) {
        this.rightSide = rightSide;
        this.leftSide = leftSide;
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }

    public Expression getRightSide() {
        return rightSide;
    }

    public Expression getLeftSide() {
        return leftSide;
    }
}
