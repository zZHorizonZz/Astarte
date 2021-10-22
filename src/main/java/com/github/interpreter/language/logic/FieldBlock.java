package com.github.interpreter.language.logic;

import com.github.interpreter.language.Constructor;
import com.github.interpreter.language.Object;
import com.github.interpreter.language.method.MethodBlock;
import com.github.interpreter.language.operator.ArithmeticalOperator;
import com.github.interpreter.parser.expression.*;
import com.github.interpreter.token.type.GenericType;
import com.github.interpreter.validation.syntax.exception.UnknownSyntaxException;

public class FieldBlock implements Block, Constructor<FieldExpression> {

    private String name;
    private GenericType genericType;
    private String customType;

    private boolean declaredFinal;
    private Expression initializer;

    private Object value;

    public FieldBlock() {
        
    }

    @Override
    public void construct(FieldExpression resource) {
        this.name = resource.getName();
        this.genericType = resource.getGenericType();
        this.customType = resource.getCustomType();
        this.declaredFinal = resource.isDeclaredFinal();

        this.initializer = resource.getInitializer();
    }

    public void initialize(MethodBlock block) {
        if (initializer == null) {
            return;
        }

        if (initializer instanceof OperatorExpression) {
            value = new ArithmeticalOperator(((OperatorExpression) initializer).operator()).process((OperatorExpression) initializer);
        } else if (initializer instanceof ReferenceExpression reference) {
            FieldBlock fieldBlock = block.getFieldMap().get(reference.name());
            if (fieldBlock != null && fieldBlock.isInitialized()) {
                if (fieldBlock.getGenericType().equals(genericType)) {
                    value = fieldBlock.getValue();
                } else {
                    throw new ClassCastException("Value can not be casted from " + fieldBlock.getGenericType().name() + " to " + genericType.name());
                }
            } else {
                throw new UnknownSyntaxException("Field block can not be recognized or is not currently initialized.");
            }
        } else if (initializer instanceof VariableExpression) {
            value = ((VariableExpression) initializer).value();
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

    public GenericType getGenericType() {
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
