package com.github.interpreter.parser.variable;

import com.github.interpreter.parser.expression.Expression;
import com.github.interpreter.parser.expression.OperatorExpression;
import com.github.interpreter.parser.expression.ReferenceExpression;
import com.github.interpreter.parser.expression.VariableExpression;
import com.github.interpreter.token.token.*;
import com.github.interpreter.validation.syntax.exception.UnknownSyntaxException;

public class VariableInitializer {

    private Expression expression;

    public VariableInitializer() {

    }

    public Expression parse(Token[] tokens) {
        if (tokens[0].hasRightSide() && tokens[0].getRightSide() instanceof SeparatorToken) {
            expression = evaluateVariable(tokens[0]);
            return expression;
        }

        return evaluateOperator(tokens);
    }

    private Expression evaluateVariable(Token token) {
        if (token instanceof LiteralToken) {
            return new VariableExpression(token.getValue());
        } else if (token instanceof IdentifierToken) {
            return new ReferenceExpression(token.getValue());
        } else {
            throw new UnknownSyntaxException("Variable has been recognized as keyword but there is problem with it's casing.");
        }
    }

    private Expression evaluateOperator(Token[] tokens) {
        if (tokens.length < 3) {
            throw new UnknownSyntaxException("Operator doesn't have enough arguments.");
        }

        Token currentToken = tokens[0];

        Expression leftSide = evaluateVariable(tokens[0]);
        Expression rightSide = evaluateVariable(tokens[2]);

        while (currentToken.hasRightSide()) {
            currentToken = currentToken.getRightSide();
            if (currentToken instanceof OperatorToken) {
                String name = currentToken.getValue();

                currentToken = currentToken.getRightSide();
                rightSide = new OperatorExpression(leftSide, evaluateVariable(currentToken), name);
            }

            if (currentToken instanceof SeparatorToken) {
                if (currentToken.getValue().equalsIgnoreCase(";")) {
                    this.expression = rightSide;
                    return expression;
                }
            }
        }

        this.expression = rightSide;
        return rightSide;
    }

    public Expression getExpression() {
        return expression;
    }
}
