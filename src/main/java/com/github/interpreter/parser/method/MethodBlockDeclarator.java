package com.github.interpreter.parser.method;

import com.github.interpreter.common.language.UtilToken;
import com.github.interpreter.parser.Declarator;
import com.github.interpreter.parser.expression.Expression;
import com.github.interpreter.parser.variable.VariableDeclarator;
import com.github.interpreter.token.token.IdentifierToken;
import com.github.interpreter.token.token.KeywordToken;
import com.github.interpreter.token.token.Token;
import com.github.interpreter.token.token.TypeToken;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MethodBlockDeclarator implements Declarator<MethodBlockDeclarator> {

    private List<Expression> body = new LinkedList<>();

    @Override
    public MethodBlockDeclarator parse(Token[] tokens) {
        if (tokens == null || tokens.length == 2) {
            return this;
        }

        for (int i = 1; i < tokens.length - 1; i++) {
            Token token = tokens[i];
            if (token instanceof KeywordToken) {

            }

            if (token instanceof TypeToken || token instanceof IdentifierToken) {
                Token[] fieldTokens = UtilToken.getBlock(Arrays.copyOfRange(tokens, i, tokens.length), null, ";");
                VariableDeclarator fieldDeclarator = new VariableDeclarator();
                Expression field = fieldDeclarator.parse(fieldTokens);

                if (field != null) {
                    body.add(field);
                }

                i += fieldTokens.length;
            }
        }

        return this;
    }

    public List<Expression> getBody() {
        return body;
    }
}
