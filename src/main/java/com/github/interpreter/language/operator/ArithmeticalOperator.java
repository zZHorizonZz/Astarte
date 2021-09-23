package com.github.interpreter.language.operator;

import com.github.interpreter.language.exception.InvalidArithmeticOperationException;
import com.github.interpreter.language.number.Number;

public class ArithmeticalOperator {

    private final String operator;

    public ArithmeticalOperator(String operator) {
        this.operator = operator;
    }

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

    public String getOperator() {
        return operator;
    }
}
