package com.github.interpreter.language.method;

import com.github.interpreter.language.logic.Block;
import com.github.interpreter.parser.method.MethodBlockDeclarator;

import java.util.LinkedList;
import java.util.List;

public class MethodBlock {

    private List<Block> block = new LinkedList<>();

    public void construct(MethodBlockDeclarator declarator) {

    }

    public List<Block> getBlock() {
        return block;
    }
}
