package com.github.interpreter.language.logic;

import com.github.interpreter.language.Constructor;
import com.github.interpreter.language.method.Method;
import com.github.interpreter.language.method.MethodBlock;
import com.github.interpreter.parser.expression.Expression;
import com.github.interpreter.parser.expression.MethodReferenceExpression;

public class ReferenceBlock implements Block, Constructor<MethodReferenceExpression> {

    private String name;
    private Expression[] arguments;

    @Override
    public void construct(MethodReferenceExpression resource) {
        name = resource.name();
        arguments = resource.arguments();
    }

    public void invoke(MethodBlock block) {
        Method method = block.getMethod().getClass0().getMethods().get(name);

        if (method == null) {
            throw new IllegalArgumentException("Method doesn't exist.");
        }

        method.invoke();
    }
}
