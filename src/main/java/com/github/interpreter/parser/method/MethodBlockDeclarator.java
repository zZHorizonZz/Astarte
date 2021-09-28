package com.github.interpreter.parser.method;

import com.github.interpreter.parser.Declarator;
import com.github.interpreter.parser.expression.Expression;
import com.github.interpreter.token.type.Token;

import java.util.LinkedList;
import java.util.List;

public class MethodBlockDeclarator implements Declarator {

    private List<Expression> body = new LinkedList<>();

    @Override
    public Declarator parse(Token[] tokens) {
        return null;
    }
}
