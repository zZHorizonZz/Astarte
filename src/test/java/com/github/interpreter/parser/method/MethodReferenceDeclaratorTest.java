package com.github.interpreter.parser.method;

import com.github.interpreter.language.number.Integer;
import com.github.interpreter.language.object.String;
import com.github.interpreter.parser.expression.MethodReferenceExpression;
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

        MethodReferenceExpression methodReferenceExpression = methodReferenceDeclarator.parse(tokenList.toArray(new Token[0]));

        Assertions.assertEquals("test", methodReferenceExpression.name());
        Assertions.assertEquals(0, methodReferenceExpression.arguments().length);
    }

    @Test
    void testNumberReference() {
        MethodReferenceDeclarator methodReferenceDeclarator = new MethodReferenceDeclarator();
        Tokenizer tokenizer = new Tokenizer("test(5);");
        List<Token> tokenList = tokenizer.tokenize();

        MethodReferenceExpression methodReferenceExpression = methodReferenceDeclarator.parse(tokenList.toArray(new Token[0]));

        Assertions.assertEquals("test", methodReferenceExpression.name());
        Assertions.assertEquals(1, methodReferenceExpression.arguments().length);
        Assertions.assertEquals(VariableExpression.class, methodReferenceExpression.arguments()[0].getClass());
        Assertions.assertEquals(Integer.class, ((VariableExpression) methodReferenceExpression.arguments()[0]).value().getClass());
        Assertions.assertEquals(5, ((Integer) ((VariableExpression) methodReferenceExpression.arguments()[0]).value()).getValue());
    }

    @Test
    void testNumberAdvancedReference() {
        MethodReferenceDeclarator methodReferenceDeclarator = new MethodReferenceDeclarator();
        Tokenizer tokenizer = new Tokenizer("test(5 + 8);");
        List<Token> tokenList = tokenizer.tokenize();

        MethodReferenceExpression methodReferenceExpression = methodReferenceDeclarator.parse(tokenList.toArray(new Token[0]));

        Assertions.assertEquals("test", methodReferenceExpression.name());
        Assertions.assertEquals(1, methodReferenceExpression.arguments().length);
        Assertions.assertEquals(OperatorExpression.class, methodReferenceExpression.arguments()[0].getClass());
        Assertions.assertEquals("+", ((OperatorExpression) methodReferenceExpression.arguments()[0]).operator());
        Assertions.assertEquals(5, ((Integer) ((VariableExpression) ((OperatorExpression) methodReferenceExpression.arguments()[0]).leftSide()).value()).getValue());
        Assertions.assertEquals(8, ((Integer) ((VariableExpression) ((OperatorExpression) methodReferenceExpression.arguments()[0]).rightSide()).value()).getValue());
    }

    @Test
    void testStringReference() {
        MethodReferenceDeclarator methodReferenceDeclarator = new MethodReferenceDeclarator();
        Tokenizer tokenizer = new Tokenizer("test(\"surprise\");");
        List<Token> tokenList = tokenizer.tokenize();

        MethodReferenceExpression methodReferenceExpression = methodReferenceDeclarator.parse(tokenList.toArray(new Token[0]));

        Assertions.assertEquals("test", methodReferenceExpression.name());
        Assertions.assertEquals(1, methodReferenceExpression.arguments().length);
        Assertions.assertEquals(VariableExpression.class, methodReferenceExpression.arguments()[0].getClass());
        Assertions.assertEquals("surprise", ((String) ((VariableExpression) methodReferenceExpression.arguments()[0]).value()).getValue());
    }
}