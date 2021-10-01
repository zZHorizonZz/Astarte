package com.github.interpreter.token.type;

import java.util.Arrays;

public enum TokenType {

    IDENTIFIER(),
    KEYWORD("if", "else", "while", "return", "class"),
    TYPE("byte", "short", "int", "long", "float", "double", "boolean", "string"),
    MODIFIER("final", "system"),
    LITERAL(),
    SEPARATOR("(", ")", ";", "{", "}", ".", ":", "::", "=>"),
    OPERATOR("+", "-", "=", "<<", ">>>", ">>"),
    UNKNOWN();

    private final String[] keywords;

    TokenType() {
        keywords = null;
    }

    TokenType(String... keyword) {
        this.keywords = keyword;
    }

    public static TokenType detectType(String word) {
        for (TokenType token : values()) {
            if (token.getKeywords() != null && token.getKeywords().length > 0) {
                for (String keyword : token.getKeywords()) {
                    if (keyword.equals(word)) {
                        return token;
                    }
                }
            }
        }

        return TokenType.UNKNOWN;
    }

    public static TokenType[] getDeterminedTokens() {
        return Arrays.stream(values()).filter(token -> token.getKeywords() != null && token.getKeywords().length > 0).toArray(TokenType[]::new);
    }

    public String[] getKeywords() {
        return keywords;
    }
}
