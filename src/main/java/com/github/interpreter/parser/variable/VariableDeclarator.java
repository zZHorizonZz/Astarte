package com.github.interpreter.parser.variable;

import com.github.interpreter.parser.Declarator;
import com.github.interpreter.parser.type.VariableTypeDeclarator;
import com.github.interpreter.token.type.IdentifierToken;
import com.github.interpreter.token.type.KeywordToken;
import com.github.interpreter.token.type.OperatorToken;
import com.github.interpreter.token.type.Token;
import com.github.interpreter.validation.syntax.exception.UnknownSyntaxException;

public class VariableDeclarator implements Declarator {

    private String name;
    private VariableTypeDeclarator type;
    private boolean declaredFinal;

    private VariableInitializer initializer;

    @Override
    public Declarator parse(Token[] tokens) {
        if (tokens == null || tokens.length < 1) {
            throw new IllegalArgumentException("Tokens passed to declarator are null or their are empty!");
        }

        int tokenIndex = 0;

        Token initializerToken = tokens[tokenIndex++];

        if (initializerToken instanceof IdentifierToken) {
            type = (VariableTypeDeclarator) new VariableTypeDeclarator(this).parse(new Token[]{initializerToken});
        } else if (initializerToken instanceof KeywordToken) {
            if (((KeywordToken) initializerToken).getKeyWord().equals(KeywordToken.KeyWord.FINAL)) {
                declaredFinal = true;
            } else {
                throw new UnknownSyntaxException("Something went wrong when initializing variable.");
            }
        }

        if (initializerToken == null) {
            initializerToken = tokens[tokenIndex++];
            if (initializerToken instanceof IdentifierToken) {
                type = (VariableTypeDeclarator) new VariableTypeDeclarator(this).parse(new Token[]{initializerToken});
            } else {
                throw new UnknownSyntaxException("Something went wrong when initializing variable.");
            }
        }

        Token nameToken = tokens[tokenIndex++];
        if (nameToken instanceof IdentifierToken) {
            name = nameToken.getValue();
        } else {
            throw new UnknownSyntaxException("Something went wrong when initializing variable.");
        }

        Token equalsToken = tokens[tokenIndex++];
        if (equalsToken instanceof OperatorToken) {
            if (!equalsToken.getValue().equalsIgnoreCase("=")) {
                throw new UnknownSyntaxException("Something went wrong when initializing variable.");
            }
        } else {
            throw new UnknownSyntaxException("Something went wrong when initializing variable.");
        }

        return null;
    }

    public String getName() {
        return name;
    }

    public VariableTypeDeclarator getType() {
        return type;
    }

    public boolean isDeclaredFinal() {
        return declaredFinal;
    }

    public VariableInitializer getInitializer() {
        return initializer;
    }
}
