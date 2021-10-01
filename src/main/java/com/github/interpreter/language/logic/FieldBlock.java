package com.github.interpreter.language.logic;

import com.github.interpreter.language.Object;
import com.github.interpreter.language.number.Byte;
import com.github.interpreter.language.number.Double;
import com.github.interpreter.language.number.Float;
import com.github.interpreter.language.number.Integer;
import com.github.interpreter.language.number.Long;
import com.github.interpreter.language.number.Short;
import com.github.interpreter.language.operator.ArithmeticalOperator;
import com.github.interpreter.parser.expression.*;
import com.github.interpreter.token.type.Type;

public class FieldBlock implements Block {

    private String name;
    private final Type genericType;
    private final String customType;

    private final boolean declaredFinal;
    private Expression initializer;

    private Object value;

    public FieldBlock(FieldExpression expression) {
        this.name = expression.getName();
        this.genericType = expression.getGenericType();
        this.customType = expression.getCustomType();
        this.declaredFinal = expression.isDeclaredFinal();

        this.initializer = expression.getInitializer();
    }

    public void initialize() {
        if (initializer instanceof OperatorExpression) {
            value = new ArithmeticalOperator(((OperatorExpression) initializer).operator()).process((OperatorExpression) initializer);
        } else if (initializer instanceof ReferenceExpression) {

        } else if (initializer instanceof VariableExpression) {
            switch (genericType) {
                case BYTE -> value = new Byte(((VariableExpression) initializer).byteValue());
                case SHORT -> value = new Short(((VariableExpression) initializer).shortValue());
                case INTEGER -> value = new Integer(((VariableExpression) initializer).intValue());
                case LONG -> value = new Long(((VariableExpression) initializer).longValue());
                case FLOAT -> value = new Float(((VariableExpression) initializer).floatValue());
                case DOUBLE -> value = new Double(((VariableExpression) initializer).doubleValue());
                case BOOLEAN -> throw new UnsupportedOperationException("This operation is not currently supported.");
            }
        }
    }

    public Object getValue() {
        return value;
    }

    public boolean isInitialized() {
        return value != null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getGenericType() {
        return genericType;
    }

    public String getCustomType() {
        return customType;
    }

    public boolean isDeclaredFinal() {
        return declaredFinal;
    }

    public Expression getInitializer() {
        return initializer;
    }

    public void setInitializer(Expression initializer) {
        this.initializer = initializer;
    }
}
