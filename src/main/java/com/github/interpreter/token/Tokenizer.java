package com.github.interpreter.token;

import com.github.interpreter.token.type.*;

import java.util.LinkedList;
import java.util.List;

public class Tokenizer {

    private final String supply;
    private final List<Token> tokenList = new LinkedList<>();

    public Tokenizer(String supply) {
        this.supply = supply;
    }

    public List<Token> tokenize() {
        if (supply == null || supply.length() == 0) {
            return tokenList;
        }

        List<String> words = new LinkedList<>();
        char[] wordsChars = supply.toCharArray();
        StringBuilder wordBuilder = new StringBuilder();

        Token lastToken = null;

        for (int i = 0; i < wordsChars.length; i++) {
            if (i > 0 && wordsChars[i - 1] != '\\' && wordsChars[i] == '"') {
                wordBuilder.append(wordsChars[i]);
                boolean end = false;
                while (!end) {
                    wordBuilder.append(wordsChars[++i]);
                    if ((wordsChars[i - 1] != '\\' && wordsChars[i] == '"')) {
                        end = true;
                    }
                }

                words.add(wordBuilder.toString());
                wordBuilder = new StringBuilder();
            } else if (wordsChars[i] != ' ') {
                wordBuilder.append(wordsChars[i++]);
                while (i + 1 < wordsChars.length && wordsChars[i] != ' ') {
                    wordBuilder.append(wordsChars[i++]);
                }

                words.add(wordBuilder.toString());
                wordBuilder = new StringBuilder();
            }
        }

        //"([\"'])(?:(?=(\\\\?))\\2.)*?\\1|([ ])
        for (String word : words) {
            /* if (word.startsWith("\"")) {
                int wordStartIndex = supply.indexOf(word);
                int wordEndIndex = 0;

                char[] string = supply.substring(wordStartIndex).toCharArray();
                for(int i = 0; i < string.length; i++) {
                    if(i > 0 && string[i - 1] != '\\' && string[i] == '"') {
                        wordEndIndex = i;
                    }
                }



                Token stringToken = evaluate(word);
                tokenList.add(stringToken);
                System.out.println("String loop value: " + stringToken.getValue());
                continue;
            }*/

            Token token = evaluate(word);
            if (lastToken != null) {
                lastToken.setLeftSide(token);
                token.setRightSide(lastToken);
            }

            lastToken = token;
            tokenList.add(token);
        }

        return tokenList;
    }

    private Token evaluate(String word) {
        if (evaluateLiteral(word)) {
            return new LiteralToken(word);
        }

        TokenType type = TokenType.detectType(word);

        switch (type) {
            case KEYWORD -> {
                return new KeywordToken(word);
            }

            case OPERATOR -> {
                return new OperatorToken(word);
            }

            case SEPARATOR -> {
                return new SeparatorToken(word);
            }
        }

        return new IdentifierToken(word);
    }

    private boolean evaluateLiteral(String word) {
        if (word.startsWith("\"") && word.endsWith("\"")) {
            return true;
        } else if (word.matches("^\\d+(\\.\\d+)?")) {
            return true;
        } else return word.matches("true") || word.matches("false");
    }

    public String getSupply() {
        return supply;
    }

    public List<Token> getTokenList() {
        return tokenList;
    }
}
