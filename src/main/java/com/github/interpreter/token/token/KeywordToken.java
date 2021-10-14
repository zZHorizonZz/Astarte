package com.github.interpreter.token.token;

import com.github.interpreter.token.type.KeyWordType;
import com.github.interpreter.token.type.TokenType;

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

    public KeyWordType getKeyWord() {
        return KeyWordType.getByName(keyword);
    }
}
