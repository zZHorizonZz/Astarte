package com.github.interpreter.parser.type;

import com.github.interpreter.parser.variable.VariableDeclarator;
import com.github.interpreter.token.type.KeyWord;
import com.github.interpreter.token.type.KeywordToken;
import com.github.interpreter.token.type.Token;
import com.github.interpreter.token.type.TokenType;
import com.github.interpreter.validation.syntax.exception.UnknownSyntaxException;
import org.apache.commons.lang3.EnumUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class VariableTypeDeclaratorTest {

    @Test
    void parse() {
        for (String keyword : TokenType.KEYWORD.getKeywords()) {
            KeyWord rightKeyWord = EnumUtils.getEnum(KeyWord.class, keyword);

            VariableTypeDeclarator declarator = new VariableTypeDeclarator(new VariableDeclarator());

            if (rightKeyWord != null) {
                Assertions.assertDoesNotThrow(() -> declarator.parse(new Token[]{new KeywordToken(keyword)}));
                Assertions.assertEquals(((VariableTypeDeclarator) declarator.parse(new Token[]{new KeywordToken(keyword)})).getName(), keyword);
            }
        }
    }
}