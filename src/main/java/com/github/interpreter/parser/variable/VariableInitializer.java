package com.github.interpreter.parser.variable;

import com.github.interpreter.parser.expression.Expression;
import com.github.interpreter.parser.expression.ReferenceExpression;
import com.github.interpreter.parser.expression.VariableExpression;
import com.github.interpreter.token.type.IdentifierToken;
import com.github.interpreter.token.type.LiteralToken;
import com.github.interpreter.token.type.Token;
import com.github.interpreter.validation.syntax.exception.UnknownSyntaxException;

public class VariableInitializer {

    private Expression expression;

    public VariableInitializer() {
    }

    public Expression parse(Token[] tokens) {
        int tokenIndex = 0;
        if (tokens.length == 1) {
            expression = evaluateVariable(tokens[tokenIndex++]);
            return expression;
        }

        Token token = tokens[tokenIndex++];

    }

    private VariableExpression evaluateVariable(Token token) {
        if (token instanceof LiteralToken) {
            return new VariableExpression(token.getValue());
        } else if (token instanceof IdentifierToken) {
            return new ReferenceExpression(token.getValue());
        } else {
            throw new UnknownSyntaxException("Variable has been recognized as keyword but there is problem with it's casing.");
        }
    }
}
