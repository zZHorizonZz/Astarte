package com.github.interpreter.token.token.literal;

import com.github.interpreter.language.object.Boolean;

public class BooleanLiteralToken extends LiteralToken<Boolean> {

    private boolean literal;

    public BooleanLiteralToken(boolean literal) {
        this.literal = literal;
    }

    @Override
    public Boolean getLiteral() {
        return new Boolean(literal);
    }

    @Override
    public void setLiteral(Boolean literal) {
        this.literal = literal.getValue();
    }
}
