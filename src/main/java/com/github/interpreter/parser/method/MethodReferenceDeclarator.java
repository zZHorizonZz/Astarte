package com.github.interpreter.parser.method;

import com.github.interpreter.common.language.UtilToken;
import com.github.interpreter.parser.Declarator;
import com.github.interpreter.parser.expression.Expression;
import com.github.interpreter.parser.expression.MethodExpression;
import com.github.interpreter.parser.variable.VariableInitializer;
import com.github.interpreter.token.token.IdentifierToken;
import com.github.interpreter.token.token.SeparatorToken;
import com.github.interpreter.token.token.Token;
import com.github.interpreter.token.token.literal.LiteralToken;
import com.github.interpreter.validation.syntax.exception.UnknownSyntaxException;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MethodReferenceDeclarator implements Declarator<MethodExpression> {

    private String name;

    private List<Expression> arguments = new LinkedList<>();
    private Expression returnType;

    @Override
    public MethodExpression parse(Token[] tokens) {
        Token identifier = tokens[0];

        if (!(identifier instanceof IdentifierToken)) {
            throw new UnknownSyntaxException("Method can not be recognized.");
        }

        name = identifier.getValue();

        if (!(identifier.getRightSide() instanceof SeparatorToken)) {
            throw new UnknownSyntaxException("Method can not be recognized.");
        }

        Token[] arguments = UtilToken.getBlock(tokens, "(", ")");
        if (arguments.length > 1) {
            parseArguments(arguments);
        }

        return new MethodExpression(name, this.arguments.toArray(Expression[]::new));
    }

    private void parseArguments(Token[] tokens) {
        Token opening = tokens[0];

        if (!(opening instanceof SeparatorToken) || !opening.getValue().equals("(")) {
            throw new UnknownSyntaxException("Method doesn't have '(' after it's name.");
        }

        if (opening.hasRightSide() && opening.getRightSide().getValue().equals(")")) {
            return;
        }

        List<Expression> arguments = new LinkedList<>();

        for (int i = 1; i < tokens.length; i++) {
            Token token = tokens[i];
            if (token instanceof SeparatorToken && token.getValue().equals(",")) {
                continue;
            }

            if (token instanceof IdentifierToken) {

            } else if (token instanceof LiteralToken) {
                VariableInitializer initializer = new VariableInitializer();
                Token[] variableTokens = UtilToken.getBlock(Arrays.copyOfRange(tokens, i, tokens.length), null, ",");
                arguments.add(initializer.parse(variableTokens));

                i += variableTokens.length;
            } else {
                throw new UnknownSyntaxException("Argument of method can not be initialized because it doesn't have identifier or type declared.");
            }
        }

        this.arguments = arguments;
    }

    public String getName() {
        return name;
    }

    public List<Expression> getArguments() {
        return arguments;
    }

    public Expression getReturnType() {
        return returnType;
    }
}
