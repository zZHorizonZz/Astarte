package com.github.interpreter.parser.type;

import com.github.interpreter.token.token.IdentifierToken;
import com.github.interpreter.token.token.KeywordToken;
import com.github.interpreter.token.token.Token;
import com.github.interpreter.token.type.*;
import org.apache.commons.lang3.EnumUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class VariablePrefixDeclaratorTest {

    @Test
    void parse() {
        for (String keyword : TokenType.KEYWORD.getKeywords()) {
            KeyWordType rightKeyWordType = EnumUtils.getEnum(KeyWordType.class, keyword);

            VariablePrefixDeclarator declarator = new VariablePrefixDeclarator();

            if (rightKeyWordType != null) {
                Assertions.assertDoesNotThrow(() -> declarator.parse(new Token[]{new KeywordToken(keyword), new IdentifierToken("field")}));
                Assertions.assertEquals(((VariablePrefixDeclarator) declarator.parse(new Token[]{new KeywordToken(keyword)})).getCustomType(), keyword);
                Assertions.assertEquals("field", declarator.getName());
            }
        }
    }
}