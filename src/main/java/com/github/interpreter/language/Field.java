package com.github.interpreter.language;

import com.github.interpreter.language.operator.ArithmeticalOperator;
import com.github.interpreter.parser.expression.Expression;
import com.github.interpreter.parser.expression.OperatorExpression;
import com.github.interpreter.token.type.TokenType;

public class Field {

    private String name;
    private TokenType returnType;

    private Expression initializer;

    public Field(String name) {
        this.name = name;
    }

    //TODO: Finish object

    public Object run() {
        if (initializer instanceof OperatorExpression) {
            return new ArithmeticalOperator(((OperatorExpression) initializer).getOperator()).process((OperatorExpression) initializer);
        }

        System.out.println("");
        return null;
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
