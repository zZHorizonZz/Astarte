package com.github.interpreter.language.logic;

import com.github.interpreter.language.Object;
import com.github.interpreter.language.method.MethodBody;
import com.github.interpreter.language.operator.ArithmeticalOperator;
import com.github.interpreter.parser.expression.Expression;
import com.github.interpreter.parser.expression.OperatorExpression;
import com.github.interpreter.parser.expression.ReferenceExpression;
import com.github.interpreter.token.type.TokenType;

public class FieldBlock implements Block {

    private final MethodBody methodBody;

    private String name;
    private TokenType returnType;

    private Expression initializer;

    public FieldBlock(MethodBody body, String name) {
        this.methodBody = body;
        this.name = name;
    }

    public Object run() {
        if (initializer instanceof OperatorExpression) {
            return new ArithmeticalOperator(((OperatorExpression) initializer).operator()).process((OperatorExpression) initializer);
        } else if (initializer instanceof ReferenceExpression) {

        }

        return null;
    }

    public MethodBody getMethodBody() {
        return methodBody;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TokenType getReturnType() {
        return returnType;
    }

    public void setReturnType(TokenType returnType) {
        this.returnType = returnType;
    }

    public Expression getInitializer() {
        return initializer;
    }

    public void setInitializer(Expression initializer) {
        this.initializer = initializer;
    }
}
