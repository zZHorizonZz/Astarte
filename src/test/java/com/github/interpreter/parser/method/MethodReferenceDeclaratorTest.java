package com.github.interpreter.parser.method;

import com.github.interpreter.language.number.Integer;
import com.github.interpreter.language.object.String;
import com.github.interpreter.parser.expression.MethodExpression;
import com.github.interpreter.parser.expression.OperatorExpression;
import com.github.interpreter.parser.expression.VariableExpression;
import com.github.interpreter.token.Tokenizer;
import com.github.interpreter.token.token.Token;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class MethodReferenceDeclaratorTest {

    @Test
    void testSimpleReference() {
        MethodReferenceDeclarator methodReferenceDeclarator = new MethodReferenceDeclarator();
        Tokenizer tokenizer = new Tokenizer("test();");
        List<Token> tokenList = tokenizer.tokenize();

        MethodExpression methodExpression = methodReferenceDeclarator.parse(tokenList.toArray(new Token[0]));

        Assertions.assertEquals("test", methodExpression.name());
        Assertions.assertEquals(0, methodExpression.arguments().length);
    }

    @Test
    void testNumberReference() {
        MethodReferenceDeclarator methodReferenceDeclarator = new MethodReferenceDeclarator();
        Tokenizer tokenizer = new Tokenizer("test(5);");
        List<Token> tokenList = tokenizer.tokenize();

        MethodExpression methodExpression = methodReferenceDeclarator.parse(tokenList.toArray(new Token[0]));

        Assertions.assertEquals("test", methodExpression.name());
        Assertions.assertEquals(1, methodExpression.arguments().length);
        Assertions.assertEquals(VariableExpression.class, methodExpression.arguments()[0].getClass());
        Assertions.assertEquals(Integer.class, ((VariableExpression) methodExpression.arguments()[0]).value().getClass());
        Assertions.assertEquals(5, ((Integer) ((VariableExpression) methodExpression.arguments()[0]).value()).getValue());
    }

    @Test
    void testNumberAdvancedReference() {
        MethodReferenceDeclarator methodReferenceDeclarator = new MethodReferenceDeclarator();
        Tokenizer tokenizer = new Tokenizer("test(5 + 8);");
        List<Token> tokenList = tokenizer.tokenize();

        MethodExpression methodExpression = methodReferenceDeclarator.parse(tokenList.toArray(new Token[0]));

        Assertions.assertEquals("test", methodExpression.name());
        Assertions.assertEquals(1, methodExpression.arguments().length);
        Assertions.assertEquals(OperatorExpression.class, methodExpression.arguments()[0].getClass());
        Assertions.assertEquals("+", ((OperatorExpression) methodExpression.arguments()[0]).operator());
        Assertions.assertEquals(5, ((Integer) ((VariableExpression) ((OperatorExpression) methodExpression.arguments()[0]).leftSide()).value()).getValue());
        Assertions.assertEquals(8, ((Integer) ((VariableExpression) ((OperatorExpression) methodExpression.arguments()[0]).rightSide()).value()).getValue());
    }

    @Test
    void testStringReference() {
        MethodReferenceDeclarator methodReferenceDeclarator = new MethodReferenceDeclarator();
        Tokenizer tokenizer = new Tokenizer("test(\"surprise\");");
        List<Token> tokenList = tokenizer.tokenize();

        MethodExpression methodExpression = methodReferenceDeclarator.parse(tokenList.toArray(new Token[0]));

        Assertions.assertEquals("test", methodExpression.name());
        Assertions.assertEquals(1, methodExpression.arguments().length);
        Assertions.assertEquals(VariableExpression.class, methodExpression.arguments()[0].getClass());
        Assertions.assertEquals("surprise", ((String) ((VariableExpression) methodExpression.arguments()[0]).value()).getValue());
    }
}