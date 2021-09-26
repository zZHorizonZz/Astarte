package com.github.interpreter.language.number;

import com.github.interpreter.language.exception.InvalidTypeException;

public class Long extends Number {

    private long value;

    public Long() {
    }

    public Long(long value) {
        this.value = value;
    }

    @Override
    public Number plus(Number number) {
        if (!(number instanceof Long)) {
            throw new InvalidTypeException();
        }

        return new Long(this.value + ((Long) number).getValue());
    }

    @Override
    public Number minus(Number number) {
        if (!(number instanceof Long)) {
            throw new InvalidTypeException();
        }

        return new Long(this.value - ((Long) number).getValue());
    }

    @Override
    public Number divide(Number number) {
        if (!(number instanceof Long)) {
            throw new InvalidTypeException();
        }

        return new Long(this.value / ((Long) number).getValue());
    }

    @Override
    public Number multiply(Number number) {
        if (!(number instanceof Long)) {
            throw new InvalidTypeException();
        }

        return new Long(this.value * ((Long) number).getValue());
    }

    public long getValue() {
        return value;
    }
}
