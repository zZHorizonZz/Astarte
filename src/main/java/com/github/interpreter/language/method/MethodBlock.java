package com.github.interpreter.language.method;

import com.github.interpreter.language.Constructor;
import com.github.interpreter.language.Object;
import com.github.interpreter.language.logic.Block;
import com.github.interpreter.language.logic.FieldBlock;
import com.github.interpreter.parser.expression.Expression;
import com.github.interpreter.parser.expression.FieldExpression;
import com.github.interpreter.parser.method.MethodBlockDeclarator;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MethodBlock implements Constructor<MethodBlockDeclarator> {

    private final Method method;

    private final List<Block> blockList = new LinkedList<>();
    private final Map<String, FieldBlock> fieldMap = new LinkedHashMap<>();

    public MethodBlock(Method method) {
        this.method = method;
    }

    @Override
    public void construct(MethodBlockDeclarator declarator) {
        if (declarator.getBody() == null) {
            return;
        }

        List<Expression> body = declarator.getBody();
        for (Expression expression : body) {
            if (expression instanceof FieldExpression) {
                FieldBlock block = new FieldBlock();
                block.construct((FieldExpression) expression);

                fieldMap.put(block.getName(), block);
                blockList.add(block);
            }
        }
    }

    public Object invoke() {
        if (blockList.isEmpty()) {
            return null;
        }

        for (Block block : blockList) {
            if (block instanceof FieldBlock) {
                ((FieldBlock) block).initialize(this);
            }
        }

        return null;
    }

    public Method getMethod() {
        return method;
    }

    public List<Block> getBlockList() {
        return blockList;
    }

    public Map<String, FieldBlock> getFieldMap() {
        return fieldMap;
    }
}
