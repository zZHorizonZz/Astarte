package com.github.interpreter.language.method;

import com.github.interpreter.parser.method.MethodDeclarator;
import com.github.interpreter.token.type.LiteralToken;
import com.github.interpreter.token.type.Token;

import java.util.List;

public class Method {

    private String name;

    private LiteralToken returnType;
    private LiteralToken[] arguments;

    private MethodBlock methodBlock;

    public Method(Token... tokens) {

    }

    public Method(List<Token> tokenList) {
        java.lang.reflect.Method method;
    }

    public void construct(MethodDeclarator declarator) {
        name = declarator.getName();

    }

}
