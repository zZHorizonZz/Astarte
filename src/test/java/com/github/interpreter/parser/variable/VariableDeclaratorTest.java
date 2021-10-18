package com.github.interpreter.parser.variable;

import com.github.interpreter.language.Object;
import com.github.interpreter.language.logic.FieldBlock;
import com.github.interpreter.language.number.Integer;
import com.github.interpreter.parser.expression.FieldExpression;
import com.github.interpreter.parser.expression.OperatorExpression;
import com.github.interpreter.token.Tokenizer;
import com.github.interpreter.token.token.Token;
import com.github.interpreter.token.type.GenericType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

class VariableDeclaratorTest {

    @Test
    void testDeclaration() {
        VariableDeclarator variableDeclarator = new VariableDeclarator();
        Tokenizer tokenizer = new Tokenizer("int field = 5 + 5;");
        List<Token> tokenList = tokenizer.tokenize();

        variableDeclarator.parse(tokenList.toArray(new Token[0]));
        Assertions.assertEquals(GenericType.INTEGER, variableDeclarator.getPrefixDeclarator().getGenericType());
        Assertions.assertEquals("field", variableDeclarator.getName());
        Assertions.assertEquals(OperatorExpression.class, variableDeclarator.getInitializer().getExpression().getClass());
    }

    @Test
    void testBuildField() {
        VariableDeclarator variableDeclarator = new VariableDeclarator();
        Tokenizer tokenizer = new Tokenizer("int fieldBlock = 5 + 5;");
        List<Token> tokenList = tokenizer.tokenize();

        FieldExpression fieldExpression = variableDeclarator.parse(tokenList.toArray(new Token[0]));

        FieldBlock fieldBlock = new FieldBlock();
        fieldBlock.construct(fieldExpression);
        fieldBlock.initialize(null); //In current context this can be null.

        Assertions.assertTrue(fieldBlock.isInitialized());
        Object number = fieldBlock.getValue();

        Assertions.assertEquals(Integer.class, number.getClass());
        Assertions.assertEquals(10, ((Integer) number).getValue());
    }

    @Test
    void testBuildRandomField() {
        Random random = new Random();
        int leftSide = random.nextInt(java.lang.Integer.MAX_VALUE / 4);
        int rightSide = random.nextInt(java.lang.Integer.MAX_VALUE / 2);

        VariableDeclarator variableDeclarator = new VariableDeclarator();
        Tokenizer tokenizer = new Tokenizer("int fieldBlock = " + leftSide + " + " + rightSide + ";");
        List<Token> tokenList = tokenizer.tokenize();

        FieldExpression fieldExpression = variableDeclarator.parse(tokenList.toArray(new Token[0]));

        FieldBlock fieldBlock = new FieldBlock();
        fieldBlock.construct(fieldExpression);
        
        fieldBlock.initialize(null);

        Assertions.assertTrue(fieldBlock.isInitialized());
        Object number = fieldBlock.getValue();

        Assertions.assertEquals(Integer.class, number.getClass());
        Assertions.assertEquals(leftSide + rightSide, ((Integer) number).getValue());
    }
}