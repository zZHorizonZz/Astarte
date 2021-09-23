package com.github.interpreter.token.type;

public enum TokenType {

    IDENTIFIER(),
    KEYWORD("if", "else", "while", "return", "final", "byte", "short", "int", "long", "float", "double", "boolean", "string"),
    LITERAL(),
    OPERATOR("+", "-", "=", "<<", ">>>", ">>"),
    SEPARATOR("(", ")", ";", "{", "}", "."),
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

    public String[] getKeywords() {
        return keywords;
    }
}
