package com.github.interpreter.token;


import com.github.interpreter.common.language.UtilType;
import com.github.interpreter.token.token.*;
import com.github.interpreter.token.token.literal.*;
import com.github.interpreter.token.type.GenericType;
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
        Token literalToken = evaluateLiteral(word);
        if (literalToken != null) {
            return literalToken;
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

    private LiteralToken<?> evaluateLiteral(String word) {
        if (word.startsWith("\"") && word.endsWith("\"")) {
            return new StringLiteralToken(word);
        } else if (NUMBER_PATTERN.matcher(word).matches()) {
            GenericType type = UtilType.getType(word);
            if (type == null) {
                return null;
            }

            switch (type) {
                case INTEGER -> {
                    return new IntegerLiteralToken(Integer.parseInt(word));
                }

                case LONG -> {
                    return new LongLiteralToken(Long.parseLong(word));
                }

                case FLOAT -> {
                    return new FloatLiteralToken(Float.parseFloat(word));
                }

                case DOUBLE -> {
                    return new DoubleLiteralToken(Double.parseDouble(word));
                }

                default -> {
                    return null;
                }
            }
        } else if (word.matches("true") || word.matches("false")) {
            return new BooleanLiteralToken(Boolean.parseBoolean(word));
        }

        return null;
    }

    public String getSupply() {
        return supply;
    }

    public List<Token> getTokenList() {
        return tokenList;
    }
}
