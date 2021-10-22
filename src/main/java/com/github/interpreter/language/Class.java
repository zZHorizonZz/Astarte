package com.github.interpreter.language;

import com.github.interpreter.common.container.Container;
import com.github.interpreter.language.method.Method;
import com.github.interpreter.parser.ClassDeclarator;

public class Class implements Constructor<ClassDeclarator> {

    private final Container<Field> fieldContainer = new Container<>();
    private final Container<Method> methodContainer = new Container<>();

    private String name;

    @Override
    public void construct(ClassDeclarator resource) {

    }

    public String getName() {
        return name;
    }

    public Container<Field> getFields() {
        return fieldContainer;
    }

    public Container<Method> getMethods() {
        return methodContainer;
    }
}
