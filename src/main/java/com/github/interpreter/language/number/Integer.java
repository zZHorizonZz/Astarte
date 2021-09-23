package com.github.interpreter.language.number;

import com.github.interpreter.language.exception.InvalidTypeException;

public class Integer extends Number {

    private int value;

    public Integer() {
    }

    public Integer(int value) {
        this.value = value;
    }

    @Override
    public Number plus(Number number) {
        if (!(number instanceof Integer)) {
            throw new InvalidTypeException();
        }

        return new Integer(this.value + ((Integer) number).getValue());
    }

    @Override
    public Number minus(Number number) {
        if (!(number instanceof Integer)) {
            throw new InvalidTypeException();
        }

        return new Integer(this.value - ((Integer) number).getValue());
    }

    @Override
    public Number divide(Number number) {
        if (!(number instanceof Integer)) {
            throw new InvalidTypeException();
        }

        return new Integer(this.value / ((Integer) number).getValue());
    }

    @Override
    public Number multiply(Number number) {
        if (!(number instanceof Integer)) {
            throw new InvalidTypeException();
        }

        return new Integer(this.value * ((Integer) number).getValue());
    }

    public int getValue() {
        return value;
    }
}
