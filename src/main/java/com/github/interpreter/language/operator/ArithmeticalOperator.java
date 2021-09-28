package com.github.interpreter.language.operator;

import com.github.interpreter.language.exception.InvalidArithmeticOperationException;
import com.github.interpreter.language.number.Number;
import com.github.interpreter.parser.expression.OperatorExpression;
import com.github.interpreter.parser.expression.VariableExpression;
import org.apache.commons.lang3.StringUtils;

public record ArithmeticalOperator(String operator) {

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
        } else if (expression.leftSide() instanceof VariableExpression && StringUtils.isNumeric(((VariableExpression) expression.leftSide()).getValue())) {
            leftSide = Number.createNumber(((VariableExpression) expression.leftSide()).getValue());
        }

        if (expression.rightSide() instanceof OperatorExpression) {
            rightSide = process((OperatorExpression) expression.rightSide());
        } else if (expression.rightSide() instanceof VariableExpression && StringUtils.isNumeric(((VariableExpression) expression.rightSide()).getValue())) {
            rightSide = Number.createNumber(((VariableExpression) expression.rightSide()).getValue());
        }

        return apply(rightSide, leftSide);
    }

    public String getOperator() {
        return operator;
    }
}
