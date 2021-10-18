package com.github.interpreter.token;

import com.github.interpreter.token.token.*;
import com.github.interpreter.token.token.literal.IntegerLiteralToken;
import com.github.interpreter.token.token.literal.LiteralToken;
import com.github.interpreter.token.token.literal.StringLiteralToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TokenizerTest {

    @Test
    public void evaluateStringField() {
        for (String keyword : new String[]{"string", "int", "long", "short", "byte", "double", "float", "boolean"}) {
            testValue(keyword, "\"Surprise\"");
            testValue(keyword, "\"Surprise non the less!\"");
            testValue(keyword, 5);
            testValue(keyword, 128);
        }
    }

    private void testValue(String keyword, String value) {
        Tokenizer tokenizer = new Tokenizer(keyword + " field = " + value + ";");
        List<Token> tokenList = tokenizer.tokenize();
        Assertions.assertEquals(TypeToken.class, tokenList.get(0).getClass());
        Assertions.assertEquals(keyword, tokenList.get(0).getValue());

        Assertions.assertEquals(IdentifierToken.class, tokenList.get(1).getClass());
        Assertions.assertEquals("field", tokenList.get(1).getValue());

        Assertions.assertEquals(OperatorToken.class, tokenList.get(2).getClass());
        Assertions.assertEquals("=", tokenList.get(2).getValue());

        Assertions.assertEquals(StringLiteralToken.class, tokenList.get(3).getClass());
        Assertions.assertEquals(value, ((StringLiteralToken) tokenList.get(3)).getLiteral().getValue());

        Assertions.assertEquals(SeparatorToken.class, tokenList.get(4).getClass());
        Assertions.assertEquals(";", tokenList.get(4).getValue());
    }

    private void testValue(String keyword, int value) {
        Tokenizer tokenizer = new Tokenizer(keyword + " field = " + value + ";");
        List<Token> tokenList = tokenizer.tokenize();
        Assertions.assertEquals(TypeToken.class, tokenList.get(0).getClass());
        Assertions.assertEquals(keyword, tokenList.get(0).getValue());

        Assertions.assertEquals(IdentifierToken.class, tokenList.get(1).getClass());
        Assertions.assertEquals("field", tokenList.get(1).getValue());

        Assertions.assertEquals(OperatorToken.class, tokenList.get(2).getClass());
        Assertions.assertEquals("=", tokenList.get(2).getValue());

        Assertions.assertEquals(IntegerLiteralToken.class, tokenList.get(3).getClass());
        Assertions.assertEquals(value, ((IntegerLiteralToken) tokenList.get(3)).getLiteral().getValue());

        Assertions.assertEquals(SeparatorToken.class, tokenList.get(4).getClass());
        Assertions.assertEquals(";", tokenList.get(4).getValue());
    }
}
