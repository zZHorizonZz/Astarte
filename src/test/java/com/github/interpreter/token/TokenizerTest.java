package com.github.interpreter.token;

import com.github.interpreter.token.type.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TokenizerTest {

    @Test
    public void evaluateStringField() {
        for (String keyword : new String[]{"string", "int", "long", "short", "byte", "double", "float", "boolean"}) {
            Tokenizer tokenizer = new Tokenizer(keyword + " field = \"Surprise\";");
            List<Token> tokenList = tokenizer.tokenize();
            Assertions.assertEquals(IdentifierToken.class, tokenList.get(0).getClass());
            Assertions.assertEquals(keyword, tokenList.get(0).getValue());

            Assertions.assertEquals(IdentifierToken.class, tokenList.get(1).getClass());
            Assertions.assertEquals("field", tokenList.get(1).getValue());

            Assertions.assertEquals(OperatorToken.class, tokenList.get(2).getClass());
            Assertions.assertEquals("=", tokenList.get(2).getValue());

            Assertions.assertEquals(LiteralToken.class, tokenList.get(3).getClass());
            Assertions.assertEquals("\"Surprise\"", tokenList.get(3).getValue());

            Assertions.assertEquals(SeparatorToken.class, tokenList.get(4).getClass());
            Assertions.assertEquals(";", tokenList.get(4).getValue());

        }
    }
}
