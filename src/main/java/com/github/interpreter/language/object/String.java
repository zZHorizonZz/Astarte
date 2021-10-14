package com.github.interpreter.language.object;

import com.github.interpreter.language.Object;

public class String extends Object {

    private java.lang.String value;

    public String() {
    }

    public String(java.lang.String value) {
        this.value = value;
    }

    public java.lang.String getValue() {
        return value;
    }
}
