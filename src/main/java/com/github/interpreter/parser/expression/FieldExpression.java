package com.github.interpreter.parser.expression;

import com.github.interpreter.parser.type.VariablePrefixDeclarator;
import com.github.interpreter.token.type.GenericType;

public class FieldExpression implements Expression {

    private String name;

    private GenericType genericType;
    private String customType;
    private boolean declaredFinal;

    private Expression initializer;

    public FieldExpression(VariablePrefixDeclarator declarator) {
        name = declarator.getName();

        genericType = declarator.getGenericType();
        customType = declarator.getCustomType();
    }

    public FieldExpression(String name) {
        this.name = name;
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

    public void setGenericType(GenericType genericType) {
        this.genericType = genericType;
    }

    public String getCustomType() {
        return customType;
    }

    public void setCustomType(String customType) {
        this.customType = customType;
    }

    public boolean isDeclaredFinal() {
        return declaredFinal;
    }

    public void setDeclaredFinal(boolean declaredFinal) {
        this.declaredFinal = declaredFinal;
    }

    public Expression getInitializer() {
        return initializer;
    }

    public void setInitializer(Expression initializer) {
        this.initializer = initializer;
    }
}
