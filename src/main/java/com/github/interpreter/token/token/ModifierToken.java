package com.github.interpreter.token.token;

import com.github.interpreter.token.type.ModifierType;
import com.github.interpreter.token.type.TokenType;

public class ModifierToken extends Token {

    private String modifier;

    public ModifierToken(String modifier) {
        super(TokenType.MODIFIER);
        this.modifier = modifier;
    }

    @Override
    public String getValue() {
        return modifier;
    }

    @Override
    public void setValue(String keyword) {
        this.modifier = keyword;
    }

    public ModifierType getModifier() {
        return ModifierType.getByName(modifier);
    }
}
