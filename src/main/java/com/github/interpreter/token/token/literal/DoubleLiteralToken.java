package com.github.interpreter.token.token.literal;

import com.github.interpreter.language.number.Double;

public class DoubleLiteralToken extends LiteralToken<Double> {

    private double literal;

    public DoubleLiteralToken(double literal) {
        this.literal = literal;
    }

    @Override
    public Double getLiteral() {
        return new Double(literal);
    }

    @Override
    public void setLiteral(Double literal) {
        this.literal = literal.getValue();
    }
}
