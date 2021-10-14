package com.github.interpreter.token;

import org.junit.jupiter.api.Test;

class TokenManagerTest {

    @Test
    void testTokenizeScript() {
        TokenManager tokenManager = new TokenManager();
        Tokenizer tokenizer = tokenManager.tokenizeScript("class Main {\n" +
                "        int testNumber = 5;\n" +
                "        String testString = \"Default String\" { get; set; }\n" +
                "\n" +
                "        Main(String name) {\n" +
                "            string testString = testReturn()[0];\n" +
                "            int testInt = testReturn()[1];\n" +
                "        }\n" +
                "\n" +
                "        init(String[] args) : int {\n" +
                "\n" +
                "        }\n" +
                "\n" +
                "        testMethod() : int => return 1;\n" +
                "\n" +
                "        testReturn() : [string, int] {\n" +
                "            return \"test\", 5;\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    class Test :: Super {\n" +
                "\n" +
                "    }\n" +
                "\n" +
                "    abstract Super {\n" +
                "\n" +
                "    }");

        System.out.println(tokenizer.getTokenList().size());
    }
}