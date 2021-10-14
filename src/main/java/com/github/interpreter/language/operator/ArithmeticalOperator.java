package com.github.interpreter.language.operator;

import com.github.interpreter.language.exception.InvalidArithmeticOperationException;
import com.github.interpreter.language.number.Number;
import com.github.interpreter.parser.expression.OperatorExpression;
import com.github.interpreter.parser.expression.VariableExpression;
import com.github.interpreter.token.type.GenericType;

public class ArithmeticalOperator {

    private final String operator;
    private GenericType type = GenericType.INTEGER;

    public ArithmeticalOperator(String operator) {
        this.operator = operator;
    }

    //TODO Think over of the variable expression and data stored inside of it.
    public Number apply(Number leftSide, Number rightSide) {
        switch (operator) {
            case ("+") -> {
                return leftSide.plus(rightSide);
            }

            case ("-") -> {
                return leftSide.minus(rightSide);
            }

            case ("*") -> {
                return leftSide.multiply(rightSide);
            }

            case ("/") -> {
                return leftSide.divide(rightSide);
            }

            default -> throw new InvalidArithmeticOperationException();
        }
    }

    public Number process(OperatorExpression expression) {
        Number leftSide = null;
        Number rightSide = null;

        if (expression.leftSide() instanceof OperatorExpression) {
            leftSide = process((OperatorExpression) expression.leftSide());
        } else if (expression.leftSide() instanceof VariableExpression && ((VariableExpression) expression.leftSide()).value() instanceof Number) {
            leftSide = (Number) ((VariableExpression) expression.leftSide()).value();
        }

        if (expression.rightSide() instanceof OperatorExpression) {
            rightSide = process((OperatorExpression) expression.rightSide());
        } else if (expression.rightSide() instanceof VariableExpression && ((VariableExpression) expression.rightSide()).value() instanceof Number) {
            rightSide = (Number) ((VariableExpression) expression.rightSide()).value();
        }

        return apply(rightSide, leftSide);
    }

    public String getOperator() {
        return operator;
    }

    public GenericType getType() {
        return type;
    }

    public void setType(GenericType type) {
        this.type = type;
    }
}
