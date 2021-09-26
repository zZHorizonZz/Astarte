package com.github.interpreter.language.number;

import com.github.interpreter.language.exception.InvalidTypeException;

public class Byte extends Number {

    private byte value;

    public Byte() {
    }

    public Byte(byte value) {
        this.value = value;
    }

    @Override
    public Number plus(Number number) {
        if (!(number instanceof Byte)) {
            throw new InvalidTypeException();
        }

        return new Byte((byte) (this.value + ((Byte) number).getValue()));
    }

    @Override
    public Number minus(Number number) {
        if (!(number instanceof Byte)) {
            throw new InvalidTypeException();
        }

        return new Byte((byte) (this.value - ((Byte) number).getValue()));
    }

    @Override
    public Number divide(Number number) {
        if (!(number instanceof Byte)) {
            throw new InvalidTypeException();
        }

        return new Byte((byte) (this.value / ((Byte) number).getValue()));
    }

    @Override
    public Number multiply(Number number) {
        if (!(number instanceof Byte)) {
            throw new InvalidTypeException();
        }

        return new Byte((byte) (this.value * ((Byte) number).getValue()));
    }

    public byte getValue() {
        return value;
    }
}