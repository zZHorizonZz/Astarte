package com.github.interpreter.language;

import com.github.interpreter.token.type.Token;

import java.util.Arrays;

public class Field {

    private final Token[] tokens;

    private String returnName;
    private Token returnType;

    private Token value;

    public Field(Token[] tokens) {
        this.tokens = tokens;
    }

    public Token run() {
        Token returnType = tokens[0];
        Token returnName = tokens[1];

        Token operator = tokens[2];

        Token[] code = Arrays.copyOfRange(tokens, 3, tokens.length);

        return null;
    }

    public Token[] getTokens() {
        return tokens;
    }
}
