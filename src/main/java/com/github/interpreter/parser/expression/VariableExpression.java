package com.github.interpreter.parser.expression;

public record VariableExpression(String value) implements Expression {

    public byte byteValue() {
        return Byte.parseByte(value);
    }

    public short shortValue() {
        return Short.parseShort(value);
    }

    public int intValue() {
        return Integer.parseInt(value);
    }

    public long longValue() {
        return Long.parseLong(value);
    }

    public float floatValue() {
        return Float.parseFloat(value);
    }

    public double doubleValue() {
        return Double.parseDouble(value);
    }

    public boolean booleanValue() {
        return Boolean.parseBoolean(value);
    }
}
