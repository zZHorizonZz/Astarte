package com.github.interpreter.language.object;

import com.github.interpreter.language.Object;

public class Boolean extends Object {

    private boolean value;

    public Boolean() {
    }

    public Boolean(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }
}
