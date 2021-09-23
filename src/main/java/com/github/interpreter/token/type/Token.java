package com.github.interpreter.token.type;

public abstract class Token {

    private Token rightSide;
    private Token leftSide;

    public abstract String getValue();

    public abstract void setValue(String value);

    public Token getRightSide() {
        return rightSide;
    }

    public void setRightSide(Token rightSide) {
        this.rightSide = rightSide;
    }

    public Token getLeftSide() {
        return leftSide;
    }

    public void setLeftSide(Token leftSide) {
        this.leftSide = leftSide;
    }
}
