package com.github.interpreter.language.method;

import com.github.interpreter.token.type.LiteralToken;
import com.github.interpreter.token.type.Token;

import java.util.List;

public class Method {

    private String name;

    private LiteralToken returnType;
    private LiteralToken[] arguments;

    private MethodContainer container;

    public Method(Token... tokens) {

    }

    public Method(List<Token> tokenList) {
        java.lang.reflect.Method method;
    }

    public void construct(Token... tokens) {
        Token returnType = tokens[0];
        Token name = tokens[1];

        Token openingColon = tokens[2];
        Token closingColon = tokens[3];

        Token methodOpeningColon = tokens[4];

        if (returnType.getValue().equals("void")) {
            this.returnType = null;
        } else {
            this.returnType = (LiteralToken) returnType;
        }
    }

}
