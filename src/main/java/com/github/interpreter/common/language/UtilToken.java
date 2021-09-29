package com.github.interpreter.common.language;

import com.github.interpreter.token.type.Token;

import java.util.Arrays;

public class UtilToken {

    public static Token[] getBlock(Token[] tokens, String opening, String closing) {
        if (tokens == null) {
            return null;
        }

        int startIndex = -1;
        int endIndex = -1;

        for (int i = 0; i < tokens.length; i++) {
            Token token = tokens[i];
            if (opening != null && token.getValue().equals(opening) && startIndex == -1) {
                startIndex = i;
            }

            if (closing != null && token.getValue().equals(closing)) {
                endIndex = i;
            }
        }

        return Arrays.copyOfRange(tokens, startIndex == -1 ? 0 : startIndex, endIndex == -1 ? tokens.length : endIndex);
    }
}
