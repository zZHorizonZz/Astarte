package com.github.interpreter.language.method;

import com.github.interpreter.language.Constructor;
import com.github.interpreter.language.logic.FieldBlock;
import com.github.interpreter.parser.expression.FieldExpression;
import com.github.interpreter.parser.method.MethodDeclarator;
import com.github.interpreter.token.type.Type;

import java.util.Arrays;

public class Method implements Constructor<MethodDeclarator> {

    private String name;

    private Type returnType;
    private FieldBlock[] arguments;

    private MethodBlock methodBlock;

    @Override
    public void construct(MethodDeclarator declarator) {
        name = declarator.getName();
        arguments = Arrays.stream(declarator.getArguments()).
                map(variableDeclarator -> new FieldBlock(new FieldExpression(variableDeclarator))).toArray(FieldBlock[]::new);

        methodBlock = new MethodBlock();
        methodBlock.construct(declarator.getBlockDeclarator());
    }

    public String getName() {
        return name;
    }

    public Type getReturnType() {
        return returnType;
    }

    public FieldBlock[] getArguments() {
        return arguments;
    }

    public MethodBlock getMethodBlock() {
        return methodBlock;
    }
}
