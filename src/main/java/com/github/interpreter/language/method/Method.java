package com.github.interpreter.language.method;

import com.github.interpreter.language.Class;
import com.github.interpreter.language.Constructor;
import com.github.interpreter.language.logic.FieldBlock;
import com.github.interpreter.parser.expression.FieldExpression;
import com.github.interpreter.parser.method.MethodDeclarator;
import com.github.interpreter.token.type.GenericType;

import java.util.Arrays;

public class Method implements Constructor<MethodDeclarator> {

    private final Class aClass;

    private String name;

    private GenericType returnGenericType;
    private FieldBlock[] arguments;

    private MethodBlock methodBlock;

    public Method(Class aClass) {
        this.aClass = aClass;
    }

    @Override
    public void construct(MethodDeclarator declarator) {
        name = declarator.getName();

        if (declarator.getArguments() != null) {
            arguments = Arrays.stream(declarator.getArguments()).
                    map(variableDeclarator -> {
                        FieldBlock fieldBlock = new FieldBlock();
                        fieldBlock.construct(new FieldExpression(variableDeclarator));
                        return fieldBlock;
                    }).toArray(FieldBlock[]::new);
        }

        methodBlock = new MethodBlock(this);
        methodBlock.construct(declarator.getBlockDeclarator());
    }

    public void invoke(Object... arguments) {
        methodBlock.invoke();
    }

    public Class getClass0() {
        return aClass;
    }

    public String getName() {
        return name;
    }

    public GenericType getReturnType() {
        return returnGenericType;
    }

    public FieldBlock[] getArguments() {
        return arguments;
    }

    public MethodBlock getMethodBlock() {
        return methodBlock;
    }
}
