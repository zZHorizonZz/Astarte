package com.github.interpreter.parser.method;

import com.github.interpreter.token.Tokenizer;
import com.github.interpreter.token.type.Token;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MethodBlockDeclaratorTest {

    @Test
    void testParse() {
        MethodBlockDeclarator blockDeclarator = new MethodBlockDeclarator();
        Tokenizer tokenizer = new Tokenizer("{ int supply = 5; }");

        blockDeclarator.parse(tokenizer.tokenize().toArray(Token[]::new));
    }
}