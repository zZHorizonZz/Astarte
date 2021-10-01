package com.github.interpreter.parser;

import com.github.interpreter.token.token.Token;

public interface Declarator<T> {

    T parse(Token[] tokens);
}
