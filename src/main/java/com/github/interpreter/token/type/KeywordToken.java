package com.github.interpreter.token.type;

public class KeywordToken extends Token {

    private String keyword;

    public KeywordToken(String keyword) {
        super(TokenType.KEYWORD);
        this.keyword = keyword;
    }

    @Override
    public String getValue() {
        return keyword;
    }

    @Override
    public void setValue(String keyword) {
        this.keyword = keyword;
    }

    public KeyWord getKeyWord() {
        return KeyWord.getByName(keyword);
    }
}
