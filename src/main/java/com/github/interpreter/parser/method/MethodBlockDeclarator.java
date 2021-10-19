package com.github.interpreter.parser.method;

import com.github.interpreter.common.language.UtilToken;
import com.github.interpreter.parser.Declarator;
import com.github.interpreter.parser.expression.Expression;
import com.github.interpreter.parser.variable.VariableDeclarator;
import com.github.interpreter.token.token.IdentifierToken;
import com.github.interpreter.token.token.OperatorToken;
import com.github.interpreter.token.token.Token;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MethodBlockDeclarator implements Declarator<MethodBlockDeclarator> {

    private final List<Expression> body = new LinkedList<>();

    @Override
    public MethodBlockDeclarator parse(Token[] tokens) {
        if (tokens == null || tokens.length == 2) {
            return this;
        }

        for (int i = 1; i < tokens.length - 1; i++) {
            Token token = tokens[i];

            switch (token.getTokenType()) {
                case KEYWORD -> {

                }

                case TYPE, IDENTIFIER -> {
                    Token[] expressionTokens = UtilToken.getBlock(Arrays.copyOfRange(tokens, i, tokens.length), null, ";");
                    Expression expression;

                    if (token instanceof IdentifierToken && token.hasRightSide() && !(token.getRightSide() instanceof OperatorToken)) {
                        MethodReferenceDeclarator methodReferenceDeclarator = new MethodReferenceDeclarator();
                        expression = methodReferenceDeclarator.parse(expressionTokens);
                    } else {
                        VariableDeclarator fieldDeclarator = new VariableDeclarator();
                        expression = fieldDeclarator.parse(expressionTokens);
                    }

                    if (expression != null) {
                        body.add(expression);
                    }

                    i += expressionTokens.length;
                }
            }
        }

        return this;
    }

    public List<Expression> getBody() {
        return body;
    }
}
