package com.github.interpreter.parser.method;

import com.github.interpreter.parser.Declarator;
import com.github.interpreter.parser.type.VariableTypeDeclarator;
import com.github.interpreter.token.type.Token;

public class MethodDeclarator implements Declarator {

    private String name;
    private VariableTypeDeclarator[] returnType;
    private VariableTypeDeclarator[] arguments;

    private MethodBlockDeclarator blockDeclarator;

    @Override
    public Declarator parse(Token[] tokens) {

    }
}
