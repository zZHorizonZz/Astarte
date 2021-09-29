package com.github.interpreter.parser.type;

import com.github.interpreter.parser.Declarator;
import com.github.interpreter.token.type.IdentifierToken;
import com.github.interpreter.token.type.Token;
import com.github.interpreter.token.type.Type;
import com.github.interpreter.validation.syntax.exception.UnknownSyntaxException;
import org.apache.commons.lang3.StringUtils;

public class VariablePrefixDeclarator implements Declarator {

    private String name;

    private String customType;
    private Type genericType;

    public VariablePrefixDeclarator() {
    }

    @Override
    public Declarator parse(Token[] tokens) {
        if (tokens == null || tokens.length != 2) {
            throw new UnknownSyntaxException("Variable declarator can not parse less or more than 1 types.");
        }

        Token token = tokens[0];
        switch (token.getTokenType()) {
            case IDENTIFIER -> {
                this.customType = token.getValue();
            }

            case TYPE -> {
                if (!StringUtils.isAllLowerCase(token.getValue())) {
                    throw new UnknownSyntaxException("Variable has been recognized as keyword but there is problem with it's casing.");
                }

                Type type = Type.getByName(token.getValue());
                if (type != null) {
                    this.genericType = type;
                    this.customType = token.getValue();
                } else {
                    throw new UnknownSyntaxException("Variable has been recognized as keyword but is not recognized as allowed variable.");
                }
            }
        }

        if (!token.hasRightSide()) {
            throw new UnknownSyntaxException("Name of variable can not be found.");
        }

        Token name = token.getRightSide();
        if (!(name instanceof IdentifierToken)) {
            throw new UnknownSyntaxException("Name of variable can not be found.");
        }

        this.name = name.getValue();

        return this;
    }

    public String getName() {
        return name;
    }

    public String getCustomType() {
        return customType;
    }

    public Type getType() {
        return genericType;
    }

    public boolean getGenericType() {
        return genericType != null;
    }

    public boolean isIdentifier() {
        return genericType == null;
    }
}
