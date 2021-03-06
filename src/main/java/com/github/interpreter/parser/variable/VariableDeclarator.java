package com.github.interpreter.parser.variable;

import com.github.interpreter.parser.Declarator;
import com.github.interpreter.parser.expression.FieldExpression;
import com.github.interpreter.parser.type.VariablePrefixDeclarator;
import com.github.interpreter.token.token.ModifierToken;
import com.github.interpreter.token.token.OperatorToken;
import com.github.interpreter.token.token.Token;
import com.github.interpreter.token.type.ModifierType;
import com.github.interpreter.validation.syntax.exception.UnknownSyntaxException;

import java.util.Arrays;

public class VariableDeclarator implements Declarator<FieldExpression> {

    private VariablePrefixDeclarator prefixDeclarator;
    private boolean declaredFinal;

    private VariableInitializer initializer;

    @Override
    public FieldExpression parse(Token[] tokens) {
        if (tokens == null || tokens.length < 1) {
            throw new IllegalArgumentException("Tokens passed to declarator are null or their are empty!");
        }

        int tokenIndex = 0;

        Token initializerToken = tokens[tokenIndex++];

        switch (initializerToken.getTokenType()) {
            case IDENTIFIER, TYPE -> {
                prefixDeclarator = new VariablePrefixDeclarator().parse(new Token[]{initializerToken, tokens[tokenIndex++]});
            }

            case KEYWORD -> {
                throw new UnsupportedOperationException("This operation is not currently supported.");
            }

            case MODIFIER -> {
                ModifierType modifier = ((ModifierToken) initializerToken).getModifier();
                if (modifier != null && modifier.equals(ModifierType.FINAL)) {
                    declaredFinal = true;
                    initializerToken = tokens[tokenIndex++];
                    prefixDeclarator = new VariablePrefixDeclarator().parse(new Token[]{initializerToken, tokens[tokenIndex++]});
                } else {
                    throw new UnknownSyntaxException("Something went wrong when initializing variable.");
                }
            }
        }

        Token equalsToken = tokens[tokenIndex++];
        if (equalsToken instanceof OperatorToken) {
            if (!equalsToken.getValue().equalsIgnoreCase("=")) {
                throw new UnknownSyntaxException("Something went wrong when initializing variable.");
            } else {
                VariableInitializer initializer = new VariableInitializer();
                initializer.parse(Arrays.copyOfRange(tokens, tokenIndex, tokens.length));
                if (initializer.getExpression() != null) {
                    this.initializer = initializer;
                }
            }
        } else {
            throw new UnknownSyntaxException("Something went wrong when initializing variable.");
        }

        FieldExpression expression = new FieldExpression(prefixDeclarator.getName());
        expression.setCustomType(prefixDeclarator.getCustomType());
        expression.setGenericType(prefixDeclarator.getGenericType());

        expression.setInitializer(initializer.getExpression());
        expression.setDeclaredFinal(declaredFinal);

        return expression;
    }

    public String getName() {
        return prefixDeclarator.getName();
    }

    public VariablePrefixDeclarator getPrefixDeclarator() {
        return prefixDeclarator;
    }

    public boolean isDeclaredFinal() {
        return declaredFinal;
    }

    public VariableInitializer getInitializer() {
        return initializer;
    }
}
