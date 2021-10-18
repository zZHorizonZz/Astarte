package com.github.interpreter.parser.method;

import com.github.interpreter.language.method.Method;
import com.github.interpreter.language.method.MethodBlock;
import com.github.interpreter.language.number.Integer;
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

    @Test
    void testParseAndFieldReference() {
        MethodDeclarator methodDeclarator = new MethodDeclarator();
        Tokenizer tokenizer = new Tokenizer("parse(string value, int number) { int test = 5; int test1 = test; }");

        MethodBlockDeclarator blockDeclarator = methodDeclarator.parse(tokenizer.tokenize().toArray(Token[]::new)).getBlockDeclarator();

        MethodBlock methodBlock = new MethodBlock(new Method());
        methodBlock.construct(blockDeclarator);

        Assertions.assertEquals(2, methodBlock.getFieldMap().size());
        methodBlock.invoke();

        Assertions.assertEquals(Integer.class, methodBlock.getFieldMap().get("test").getValue().getClass());
        Assertions.assertEquals(5, ((Integer) methodBlock.getFieldMap().get("test").getValue()).getValue());
        Assertions.assertEquals(Integer.class, methodBlock.getFieldMap().get("test1").getValue().getClass());
        Assertions.assertEquals(5, ((Integer) methodBlock.getFieldMap().get("test1").getValue()).getValue());
    }
}