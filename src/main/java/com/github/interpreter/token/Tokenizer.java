package com.github.interpreter.token;


import com.github.interpreter.token.token.*;
import com.github.interpreter.token.type.TokenType;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class Tokenizer {

    public static final Pattern NUMBER_PATTERN = Pattern.compile("^\\d+(\\.\\d+)?");

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

        master:
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
            } else {
                if (wordsChars[i] == ' ') {
                    continue;
                }

                wordBuilder.append(wordsChars[i]);
                for (TokenType genericType : TokenType.getDeterminedTokens()) {
                    for (String keyword : genericType.getKeywords()) {
                        String word = wordBuilder.toString();

                        if (word.equals(keyword)) {
                            words.add(keyword);
                            wordBuilder = new StringBuilder();
                            continue master;
                        } else if (word.startsWith(keyword)) {
                            words.add(word.substring(keyword.length()));
                            words.add(keyword);
                            wordBuilder = new StringBuilder();
                        } else if (word.endsWith(keyword)) {
                            words.add(word.substring(0, word.lastIndexOf(keyword)));
                            words.add(keyword);
                            wordBuilder = new StringBuilder();
                            continue master;
                        }
                    }
                }

                if (wordsChars[i + 1] == ' ') {
                    words.add(wordBuilder.toString());
                    wordBuilder = new StringBuilder();
                    i++;
                }
            }
        }

        //"([\"'])(?:(?=(\\\\?))\\2.)*?\\1|([ ])
        for (String word : words) {
            Token token = evaluate(word);
            if (lastToken != null) {
                lastToken.setRightSide(token);
                token.setLeftSide(lastToken);
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

        TokenType genericType = TokenType.detectType(word);

        switch (genericType) {
            case KEYWORD -> {
                return new KeywordToken(word);
            }

            case OPERATOR -> {
                return new OperatorToken(word);
            }

            case SEPARATOR -> {
                return new SeparatorToken(word);
            }

            case TYPE -> {
                return new TypeToken(word);
            }
        }

        return new IdentifierToken(word);
    }

    private boolean evaluateLiteral(String word) {
        if (word.startsWith("\"") && word.endsWith("\"")) {
            return true;
        } else if (NUMBER_PATTERN.matcher(word).matches()) {
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
