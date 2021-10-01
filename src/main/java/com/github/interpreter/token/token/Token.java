package com.github.interpreter.token.token;

import com.github.interpreter.token.type.TokenType;

public abstract class Token {

    private final TokenType tokenType;

    private Token rightSide;
    private Token leftSide;

    public Token(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public abstract String getValue();

    public abstract void setValue(String value);

    public TokenType getTokenType() {
        return tokenType;
    }

    public Token getRightSide() {
        return rightSide;
    }

    public void setRightSide(Token rightSide) {
        this.rightSide = rightSide;
    }

    public boolean hasRightSide() {
        return rightSide != null;
    }

    public Token getLeftSide() {
        return leftSide;
    }

    public void setLeftSide(Token leftSide) {
        this.leftSide = leftSide;
    }

    public boolean hasLeftSide() {
        return leftSide != null;
    }
}
