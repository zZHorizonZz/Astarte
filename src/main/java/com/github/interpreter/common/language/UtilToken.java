package com.github.interpreter.common.language;

import com.github.interpreter.token.token.Token;

import java.util.Arrays;

public class UtilToken {

    public static Token[] getBlock(Token[] tokens, String opening, String closing) {
        if (tokens == null) {
            return null;
        }

        int startIndex = -1;
        int endIndex = -1;

        int openingCount = 0;
        int closingCount = 0;

        for (int i = 0; i < tokens.length; i++) {
            Token token = tokens[i];
            if (opening != null && token.getValue().equals(opening) && startIndex == -1) {
                startIndex = i;
            }

            if (opening != null && token.getValue().equals(opening)) {
                openingCount++;
            }

            if (closing != null && token.getValue().equals(closing)) {
                endIndex = i;
                closingCount++;
            }

            if ((closingCount == openingCount || closingCount > openingCount) && endIndex > -1) {
                break;
            }
        }
        
        return Arrays.copyOfRange(tokens, startIndex == -1 ? 0 : startIndex, endIndex == -1 ? tokens.length : endIndex);
    }
}
