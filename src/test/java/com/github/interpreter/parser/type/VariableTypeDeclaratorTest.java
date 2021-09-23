package com.github.interpreter.parser.type;

import com.github.interpreter.parser.variable.VariableDeclarator;
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
            KeywordToken.KeyWord badKeyWord = EnumUtils.getEnum(KeywordToken.KeyWord.class, keyword);
            KeywordToken.VariableKeyWord rightKeyWord = EnumUtils.getEnum(KeywordToken.VariableKeyWord.class, keyword);

            VariableTypeDeclarator declarator = new VariableTypeDeclarator(new VariableDeclarator());
            if (badKeyWord != null) {
                Assertions.assertThrows(UnknownSyntaxException.class, () -> declarator.parse(new Token[]{new KeywordToken(keyword)}));
                continue;
            }

            if (rightKeyWord != null) {
                Assertions.assertDoesNotThrow(() -> declarator.parse(new Token[]{new KeywordToken(keyword)}));
                Assertions.assertEquals(((VariableTypeDeclarator) declarator.parse(new Token[]{new KeywordToken(keyword)})).getName(), keyword);
            }
        }
    }
}