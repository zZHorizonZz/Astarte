package com.github.interpreter.parser.method;

import com.github.interpreter.token.Tokenizer;
import com.github.interpreter.token.token.Token;
import com.github.interpreter.token.type.GenericType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MethodDeclaratorTest {

    @Test
    void testSimpleParse() {
        MethodDeclarator declarator = new MethodDeclarator();
        Tokenizer tokenizer = new Tokenizer("parse() { int test = 5; }");

        declarator.parse(tokenizer.tokenize().toArray(Token[]::new));

        Assertions.assertEquals("parse", declarator.getName());
    }

    @Test
    void testArgumentParse() {
        MethodDeclarator declarator = new MethodDeclarator();
        Tokenizer tokenizer = new Tokenizer("parse(string value) { int test = 5; }");

        declarator.parse(tokenizer.tokenize().toArray(Token[]::new));

        Assertions.assertEquals("parse", declarator.getName());
        Assertions.assertEquals(1, declarator.getArguments().length);
        Assertions.assertEquals(GenericType.STRING, declarator.getArguments()[0].getGenericType());
    }

    @Test
    void testMultipleArgumentParse() {
        MethodDeclarator declarator = new MethodDeclarator();
        Tokenizer tokenizer = new Tokenizer("parse(string value, int number) { int test = 5; }");

        declarator.parse(tokenizer.tokenize().toArray(Token[]::new));

        Assertions.assertEquals("parse", declarator.getName());
        Assertions.assertEquals(2, declarator.getArguments().length);
        Assertions.assertEquals(GenericType.STRING, declarator.getArguments()[0].getGenericType());
        Assertions.assertEquals(GenericType.INTEGER, declarator.getArguments()[1].getGenericType());
    }
}