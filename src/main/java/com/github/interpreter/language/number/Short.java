package com.github.interpreter.language.number;

import com.github.interpreter.language.exception.InvalidTypeException;

public class Short extends Number {

    private short value;

    public Short() {
    }

    public Short(short value) {
        this.value = value;
    }

    @Override
    public Number plus(Number number) {
        if (!(number instanceof Short)) {
            throw new InvalidTypeException();
        }

        return new Short((short) (this.value + ((Short) number).getValue()));
    }

    @Override
    public Number minus(Number number) {
        if (!(number instanceof Short)) {
            throw new InvalidTypeException();
        }

        return new Short((short) (this.value - ((Short) number).getValue()));
    }

    @Override
    public Number divide(Number number) {
        if (!(number instanceof Short)) {
            throw new InvalidTypeException();
        }

        return new Short((short) (this.value / ((Short) number).getValue()));
    }

    @Override
    public Number multiply(Number number) {
        if (!(number instanceof Short)) {
            throw new InvalidTypeException();
        }

        return new Short((short) (this.value * ((Short) number).getValue()));
    }

    public short getValue() {
        return value;
    }
}
