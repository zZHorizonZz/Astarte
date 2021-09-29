package com.github.interpreter.parser.method;

import com.github.interpreter.common.language.UtilToken;
import com.github.interpreter.parser.Declarator;
import com.github.interpreter.parser.type.VariablePrefixDeclarator;
import com.github.interpreter.token.type.IdentifierToken;
import com.github.interpreter.token.type.SeparatorToken;
import com.github.interpreter.token.type.Token;
import com.github.interpreter.validation.syntax.exception.UnknownSyntaxException;

public class MethodDeclarator implements Declarator {

    private String name;
    private VariablePrefixDeclarator[] returnType;
    private VariablePrefixDeclarator[] arguments;

    private MethodBlockDeclarator blockDeclarator;

    @Override
    public Declarator parse(Token[] tokens) {
        Token identifier = tokens[0];

        if (!(identifier instanceof IdentifierToken)) {
            throw new UnknownSyntaxException("Method can not be recognized.");
        }

        name = identifier.getValue();

        if (!(identifier.getRightSide() instanceof SeparatorToken)) {
            throw new UnknownSyntaxException("Method can not be recognized.");
        }

        Token[] arguments = UtilToken.getBlock(tokens, "(", ")");
        if (arguments.length > 2) {
            parseArguments(arguments);
        }

        return null;
    }

    private void parseArguments(Token[] tokens) {

    }
}
