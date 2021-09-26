package com.github.interpreter.parser.variable;

import com.github.interpreter.language.Field;
import com.github.interpreter.language.Object;
import com.github.interpreter.language.number.Integer;
import com.github.interpreter.parser.expression.OperatorExpression;
import com.github.interpreter.token.Tokenizer;
import com.github.interpreter.token.type.Token;
import com.github.interpreter.token.type.Type;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class VariableDeclaratorTest {

    @Test
    void testDeclaration() {
        VariableDeclarator variableDeclarator = new VariableDeclarator();
        Tokenizer tokenizer = new Tokenizer("int field = 5 + 5;");
        List<Token> tokenList = tokenizer.tokenize();

        variableDeclarator = (VariableDeclarator) variableDeclarator.parse(tokenList.toArray(new Token[0]));
        Assertions.assertEquals(Type.INTEGER, variableDeclarator.getType().getType());
        Assertions.assertEquals("field", variableDeclarator.getName());
        Assertions.assertEquals(OperatorExpression.class, variableDeclarator.getInitializer().getExpression().getClass());
    }

    @Test
    void testBuildField() {
        VariableDeclarator variableDeclarator = new VariableDeclarator();
        Tokenizer tokenizer = new Tokenizer("int field = 5 + 5;");
        List<Token> tokenList = tokenizer.tokenize();

        variableDeclarator = (VariableDeclarator) variableDeclarator.parse(tokenList.toArray(new Token[0]));

        Field field = variableDeclarator.buildField();
        Object number = field.run();

        Assertions.assertEquals(Integer.class, number.getClass());
        Assertions.assertEquals(10, ((Integer) number).getValue());
    }
}