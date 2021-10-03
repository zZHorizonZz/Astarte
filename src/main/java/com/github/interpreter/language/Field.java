package com.github.interpreter.language;

import com.github.interpreter.language.logic.FieldBlock;
import com.github.interpreter.parser.expression.FieldExpression;

public class Field implements Constructor<FieldExpression> {

    private FieldBlock fieldBlock;

    @Override
    public void construct(FieldExpression resource) {

    }

    public FieldBlock getFieldBlock() {
        return fieldBlock;
    }
}

