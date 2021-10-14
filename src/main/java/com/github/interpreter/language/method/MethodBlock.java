package com.github.interpreter.language.method;

import com.github.interpreter.language.Constructor;
import com.github.interpreter.language.logic.Block;
import com.github.interpreter.language.logic.FieldBlock;
import com.github.interpreter.parser.expression.Expression;
import com.github.interpreter.parser.expression.FieldExpression;
import com.github.interpreter.parser.method.MethodBlockDeclarator;

import java.util.LinkedList;
import java.util.List;

//TODO Add something like block identifier.
public class MethodBlock implements Constructor<MethodBlockDeclarator> {

    private final List<Block> blockList = new LinkedList<>();

    @Override
    public void construct(MethodBlockDeclarator declarator) {
        if (declarator.getBody() == null) {
            return;
        }

        List<Expression> body = declarator.getBody();
        for (Expression expression : body) {
            if (expression instanceof FieldExpression) {
                blockList.add(new FieldBlock((FieldExpression) expression));
            }
        }
    }

    public void invoke() {
        if (blockList.isEmpty()) {
            return;
        }

        for (Block block : blockList) {
            if (block instanceof FieldBlock) {
                ((FieldBlock) block).initialize();
            }
        }
    }

    public List<Block> getBlockList() {
        return blockList;
    }
}
