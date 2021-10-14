package com.github.interpreter.token.token.literal;

import com.github.interpreter.language.number.Float;

public class FloatLiteralToken extends LiteralToken<Float> {

    private float literal;

    public FloatLiteralToken(float literal) {
        this.literal = literal;
    }

    @Override
    public Float getLiteral() {
        return new Float(literal);
    }

    @Override
    public void setLiteral(Float literal) {
        this.literal = literal.getValue();
    }
}
