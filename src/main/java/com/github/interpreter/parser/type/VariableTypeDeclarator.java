package com.github.interpreter.parser.type;

import com.github.interpreter.parser.Declarator;
import com.github.interpreter.parser.variable.VariableDeclarator;
import com.github.interpreter.token.type.Token;
import com.github.interpreter.token.type.Type;
import com.github.interpreter.validation.syntax.exception.UnknownSyntaxException;
import org.apache.commons.lang3.StringUtils;

public class VariableTypeDeclarator implements Declarator {

    private final VariableDeclarator declarator;
    private String name;

    private Type type;

    public VariableTypeDeclarator(VariableDeclarator declarator) {
        this.declarator = declarator;
    }

    @Override
    public Declarator parse(Token[] tokens) {
        if (tokens == null || tokens.length != 1) {
            throw new UnknownSyntaxException("Variable declarator can not parse less or more than 1 types.");
        }

        Token token = tokens[0];
        switch (token.getTokenType()) {
            case IDENTIFIER -> {
                this.name = token.getValue();
            }

            case TYPE -> {
                if (!StringUtils.isAllLowerCase(token.getValue())) {
                    throw new UnknownSyntaxException("Variable has been recognized as keyword but there is problem with it's casing.");
                }

                Type type = Type.getByName(token.getValue());
                if (type != null) {
                    this.type = type;
                    this.name = token.getValue();
                } else {
                    throw new UnknownSyntaxException("Variable has been recognized as keyword but is not recognized as allowed variable.");
                }
            }
        }

        return this;
    }

    public VariableDeclarator getDeclarator() {
        return declarator;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public boolean isType() {
        return type != null;
    }

    public boolean isIdentifier() {
        return type == null;
    }
}
