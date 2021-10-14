package com.github.interpreter.parser.type;

import com.github.interpreter.parser.Declarator;
import com.github.interpreter.token.token.IdentifierToken;
import com.github.interpreter.token.token.Token;
import com.github.interpreter.token.type.GenericType;
import com.github.interpreter.validation.syntax.exception.UnknownSyntaxException;
import org.apache.commons.lang3.StringUtils;

public class VariablePrefixDeclarator implements Declarator<VariablePrefixDeclarator> {

    private String name;

    private String customType;
    private GenericType genericType;

    public VariablePrefixDeclarator() {
    }

    @Override
    public VariablePrefixDeclarator parse(Token[] tokens) {
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

                GenericType genericType = GenericType.getByName(token.getValue());
                if (genericType != null) {
                    this.genericType = genericType;
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

    public GenericType getGenericType() {
        return genericType;
    }

    public boolean isGenericType() {
        return genericType != null;
    }

    public boolean isIdentifier() {
        return genericType == null;
    }
}
