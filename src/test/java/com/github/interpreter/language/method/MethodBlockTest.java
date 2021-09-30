package com.github.interpreter.language.method;

import com.github.interpreter.language.logic.FieldBlock;
import com.github.interpreter.parser.method.MethodBlockDeclarator;
import com.github.interpreter.token.Tokenizer;
import com.github.interpreter.token.type.Token;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MethodBlockTest {

    @Test
    void testConstruct() {
        MethodBlockDeclarator blockDeclarator = new MethodBlockDeclarator();
        Tokenizer tokenizer = new Tokenizer("{ " +
                "int supply = 5;" +
                "string testString = \"Super\";" +
                " }");

        blockDeclarator.parse(tokenizer.tokenize().toArray(Token[]::new));

        MethodBlock block = new MethodBlock();
        block.construct(blockDeclarator);

        Assertions.assertEquals(2, block.getBlockList().size());
        Assertions.assertEquals(FieldBlock.class, block.getBlockList().get(0).getClass());
        Assertions.assertEquals(FieldBlock.class, block.getBlockList().get(1).getClass());
    }

    @Test
    void testInvoke() {
    }
}