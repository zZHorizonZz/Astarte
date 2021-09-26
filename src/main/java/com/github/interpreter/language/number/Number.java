package com.github.interpreter.language.number;

import com.github.interpreter.language.Object;

public abstract class Number extends Object {

    public abstract Number plus(Number number);

    public abstract Number minus(Number number);

    public abstract Number divide(Number number);

    public abstract Number multiply(Number number);

    public static Number createNumber(String string) {
        if (string == null) {
            return null;
        }

        boolean decimal = false;
        boolean f = false;

        if (string.contains(".")) {
            decimal = true;
        }

        if (string.endsWith("f") || string.endsWith("F")) {
            f = true;
        }

        if (decimal) {
            if (f) {
                return new Float(java.lang.Float.parseFloat(string));
            }

            return new Double(java.lang.Double.parseDouble(string));
        }

        return new Integer(java.lang.Integer.decode(string));
    }
}
