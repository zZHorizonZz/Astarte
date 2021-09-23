package com.github.interpreter.language.number;

import com.github.interpreter.language.Object;

public abstract class Number extends Object {

    public abstract Number plus(Number number);

    public abstract Number minus(Number number);

    public abstract Number divide(Number number);

    public abstract Number multiply(Number number);
}
