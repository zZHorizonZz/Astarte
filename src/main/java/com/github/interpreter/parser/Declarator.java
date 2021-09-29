package com.github.interpreter.parser;

import com.github.interpreter.token.type.Token;

public interface Declarator<T> {

    T parse(Token[] tokens);
}
