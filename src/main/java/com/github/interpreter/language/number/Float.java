package com.github.interpreter.language.number;

import com.github.interpreter.language.exception.InvalidTypeException;

public class Float extends Number {

    private float value;

    public Float() {
    }

    public Float(float value) {
        this.value = value;
    }

    @Override
    public Number plus(Number number) {
        if (!(number instanceof Float)) {
            throw new InvalidTypeException();
        }

        return new Float(this.value + ((Float) number).getValue());
    }

    @Override
    public Number minus(Number number) {
        if (!(number instanceof Float)) {
            throw new InvalidTypeException();
        }

        return new Float(this.value - ((Float) number).getValue());
    }

    @Override
    public Number divide(Number number) {
        if (!(number instanceof Float)) {
            throw new InvalidTypeException();
        }

        return new Float(this.value / ((Float) number).getValue());
    }

    @Override
    public Number multiply(Number number) {
        if (!(number instanceof Float)) {
            throw new InvalidTypeException();
        }

        return new Float(this.value * ((Float) number).getValue());
    }

    public float getValue() {
        return value;
    }
}