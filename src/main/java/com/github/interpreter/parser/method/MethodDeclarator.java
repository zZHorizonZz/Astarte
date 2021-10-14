package com.github.interpreter.parser.method;

import com.github.interpreter.common.language.UtilToken;
import com.github.interpreter.parser.Declarator;
import com.github.interpreter.parser.type.VariablePrefixDeclarator;
import com.github.interpreter.token.token.IdentifierToken;
import com.github.interpreter.token.token.SeparatorToken;
import com.github.interpreter.token.token.Token;
import com.github.interpreter.token.token.TypeToken;
import com.github.interpreter.validation.syntax.exception.UnknownSyntaxException;

import java.util.LinkedList;
import java.util.List;

public class MethodDeclarator implements Declarator<MethodDeclarator> {

    private String name;
    private VariablePrefixDeclarator[] returnType;
    private VariablePrefixDeclarator[] arguments;

    private MethodBlockDeclarator blockDeclarator;

    @Override
    public MethodDeclarator parse(Token[] tokens) {
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

        Token[] block = UtilToken.getBlock(tokens, "{", "}");

        blockDeclarator = new MethodBlockDeclarator();
        blockDeclarator.parse(block);

        return this;
    }

    private void parseArguments(Token[] tokens) {
        Token opening = tokens[0];

        if (!(opening instanceof SeparatorToken) || !opening.getValue().equals("(")) {
            throw new UnknownSyntaxException("Method doesn't have '(' after it's name.");
        }

        if (opening.hasRightSide() && opening.getRightSide().getValue().equals(")")) {
            return;
        }

        List<VariablePrefixDeclarator> arguments = new LinkedList<>();

        for (int i = 1; i < tokens.length; i++) {
            Token token = tokens[i];
            if (token instanceof SeparatorToken && token.getValue().equals(",")) {
                continue;
            }

            if (token instanceof IdentifierToken || token instanceof TypeToken) {
                VariablePrefixDeclarator argument = new VariablePrefixDeclarator();
                argument.parse(new Token[]{token, tokens[i++]});

                arguments.add(argument);
            } else {
                throw new UnknownSyntaxException("Argument of method can not be initialized because it doesn't have identifier or type declared.");
            }
        }

        this.arguments = arguments.toArray(VariablePrefixDeclarator[]::new);
    }

    public String getName() {
        return name;
    }

    public VariablePrefixDeclarator[] getReturnType() {
        return returnType;
    }

    public VariablePrefixDeclarator[] getArguments() {
        return arguments;
    }

    public MethodBlockDeclarator getBlockDeclarator() {
        return blockDeclarator;
    }
}
