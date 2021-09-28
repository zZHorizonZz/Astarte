package com.github.interpreter.parser.variable;

import com.github.interpreter.language.logic.FieldBlock;
import com.github.interpreter.language.method.MethodBody;
import com.github.interpreter.parser.Declarator;
import com.github.interpreter.parser.type.VariableTypeDeclarator;
import com.github.interpreter.token.type.*;
import com.github.interpreter.validation.syntax.exception.UnknownSyntaxException;

import java.util.Arrays;

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

        switch (initializerToken.getTokenType()) {
            case IDENTIFIER, TYPE -> {
                type = (VariableTypeDeclarator) new VariableTypeDeclarator(this).parse(new Token[]{initializerToken});
            }

            case KEYWORD -> {
                KeyWord keyWord = ((KeywordToken) initializerToken).getKeyWord();
                if (keyWord != null && keyWord.equals(KeyWord.FINAL)) {
                    declaredFinal = true;
                    initializerToken = tokens[tokenIndex++];
                    type = (VariableTypeDeclarator) new VariableTypeDeclarator(this).parse(new Token[]{initializerToken});
                } else {
                    throw new UnknownSyntaxException("Something went wrong when initializing variable.");
                }
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

        return this;
    }

    public FieldBlock buildField() {
        FieldBlock fieldBlock = new FieldBlock(new MethodBody(), name);
        fieldBlock.setInitializer(initializer.getExpression());
        //fieldBlock.setReturnType(type.getType());
        return fieldBlock;
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
