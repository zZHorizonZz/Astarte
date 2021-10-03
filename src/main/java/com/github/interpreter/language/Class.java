package com.github.interpreter.language;

import com.github.interpreter.language.method.Method;
import com.github.interpreter.parser.ClassDeclarator;

public class Class implements Constructor<ClassDeclarator> {

    private String name;

    private Field[] fields;
    private Method[] methods;

    @Override
    public void construct(ClassDeclarator resource) {

    }

    public String getName() {
        return name;
    }

    public Field[] getFields() {
        return fields;
    }

    public Method[] getMethods() {
        return methods;
    }
}
