package com.github.interpreter.parser;

import com.github.interpreter.token.type.Token;

public interface Declarator {

    Declarator parse(Token[] tokens);
}
