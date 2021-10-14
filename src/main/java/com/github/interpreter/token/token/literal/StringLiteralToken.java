package com.github.interpreter.token.token.literal;

import com.github.interpreter.language.object.String;

public class StringLiteralToken extends LiteralToken<String> {

    private java.lang.String literal;

    public StringLiteralToken(java.lang.String literal) {
        this.literal = literal;
    }

    @Override
    public String getLiteral() {
        return new String(literal);
    }

    @Override
    public void setLiteral(String literal) {
        this.literal = literal.getValue();
    }
}
