package com.github.interpreter.parser.variable;

import com.github.interpreter.language.number.Integer;
import com.github.interpreter.parser.expression.Expression;
import com.github.interpreter.parser.expression.OperatorExpression;
import com.github.interpreter.parser.expression.VariableExpression;
import com.github.interpreter.token.Tokenizer;
import com.github.interpreter.token.token.Token;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class VariableInitializerTest {

    @Test
    void testSimpleParse() {
        VariableInitializer initializer = new VariableInitializer();
        Tokenizer tokenizer = new Tokenizer("int field = 5;");
        List<Token> tokenList = tokenizer.tokenize();

        Expression expression = initializer.parse(Arrays.copyOfRange(tokenList.toArray(new Token[0]), 3, 5));
        Assertions.assertEquals(VariableExpression.class, expression.getClass());
        Assertions.assertEquals(5, ((Integer) ((VariableExpression) expression).value()).getValue());
    }

    @Test
    void testMediumParse() {
        VariableInitializer initializer = new VariableInitializer();
        Tokenizer tokenizer = new Tokenizer("int field = 5 + 8;");
        List<Token> tokenList = tokenizer.tokenize();

        Expression expression = initializer.parse(Arrays.copyOfRange(tokenList.toArray(new Token[0]), 3, tokenList.size()));
        Assertions.assertEquals(OperatorExpression.class, expression.getClass());
        Assertions.assertEquals("+", ((OperatorExpression) expression).operator());
        Assertions.assertEquals(VariableExpression.class, ((OperatorExpression) expression).leftSide().getClass());
        Assertions.assertEquals(5, ((Integer) ((VariableExpression) ((OperatorExpression) expression).leftSide()).value()).getValue());
        Assertions.assertEquals(VariableExpression.class, ((OperatorExpression) expression).rightSide().getClass());
        Assertions.assertEquals(8, ((Integer) ((VariableExpression) ((OperatorExpression) expression).rightSide()).value()).getValue());
    }
}