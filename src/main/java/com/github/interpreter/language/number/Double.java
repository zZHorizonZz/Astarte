package com.github.interpreter.language.number;

import com.github.interpreter.language.exception.InvalidTypeException;

public class Double extends Number {

    private double value;

    public Double() {
    }

    public Double(double value) {
        this.value = value;
    }

    @Override
    public Number plus(Number number) {
        if (!(number instanceof Double)) {
            throw new InvalidTypeException();
        }

        return new Double(this.value + ((Double) number).getValue());
    }

    @Override
    public Number minus(Number number) {
        if (!(number instanceof Double)) {
            throw new InvalidTypeException();
        }

        return new Double(this.value - ((Double) number).getValue());
    }

    @Override
    public Number divide(Number number) {
        if (!(number instanceof Double)) {
            throw new InvalidTypeException();
        }

        return new Double(this.value / ((Double) number).getValue());
    }

    @Override
    public Number multiply(Number number) {
        if (!(number instanceof Double)) {
            throw new InvalidTypeException();
        }

        return new Double(this.value * ((Double) number).getValue());
    }

    public double getValue() {
        return value;
    }
}
