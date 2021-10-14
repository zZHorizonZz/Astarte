package com.github.interpreter.token.token.literal;

import com.github.interpreter.language.number.Long;

public class LongLiteralToken extends LiteralToken<Long> {

    private long literal;

    public LongLiteralToken(long literal) {
        this.literal = literal;
    }

    @Override
    public Long getLiteral() {
        return new Long(literal);
    }

    @Override
    public void setLiteral(Long literal) {
        this.literal = literal.getValue();
    }
}
