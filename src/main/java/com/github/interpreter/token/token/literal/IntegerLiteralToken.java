package com.github.interpreter.token.token.literal;

import com.github.interpreter.language.number.Integer;

public class IntegerLiteralToken extends LiteralToken<Integer> {

    private int literal;

    public IntegerLiteralToken(int literal) {
        this.literal = literal;
    }

    @Override
    public Integer getLiteral() {
        return new Integer(literal);
    }

    @Override
    public void setLiteral(Integer literal) {
        this.literal = literal.getValue();
    }
}
