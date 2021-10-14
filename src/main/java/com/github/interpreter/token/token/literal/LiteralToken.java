package com.github.interpreter.token.token.literal;

import com.github.interpreter.language.Object;
import com.github.interpreter.token.token.Token;
import com.github.interpreter.token.type.TokenType;

public abstract class LiteralToken<T extends Object> extends Token {

    public LiteralToken() {
        super(TokenType.LITERAL);
    }

    @Override
    public String getValue() {
        return String.valueOf(getLiteral());
    }

    @Override
    public void setValue(String value) {
        throw new UnsupportedOperationException("This operation is not supported by literal token.");
    }

    public abstract T getLiteral();

    public abstract void setLiteral(T literal);
}
