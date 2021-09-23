package com.github.interpreter.token.type;

public class KeywordToken implements Token {

    private String keyword;

    public KeywordToken(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String getValue() {
        return keyword;
    }

    @Override
    public void setValue(String keyword) {
        this.keyword = keyword;
    }

    public KeyWord getKeyWord() {
        return KeyWord.valueOf(keyword);
    }

    public enum KeyWord {
        IF("if"),
        ELSE("else"),
        WHILE("while"),
        RETURN("return"),
        FINAL("final");

        private final String keyword;

        KeyWord(String keyword) {
            this.keyword = keyword;
        }

        public String getKeyword() {
            return keyword;
        }
    }

    public enum VariableKeyWord {
        BYTE("byte"),
        SHORT("short"),
        INT("int"),
        LONG("long"),
        FLOAT("float"),
        DOUBLE("double"),
        BOOLEAN("boolean"),
        STRING("string");

        private final String variableKeyWord;

        VariableKeyWord(String variableKeyWord) {
            this.variableKeyWord = variableKeyWord;
        }

        public String getVariableKeyWord() {
            return variableKeyWord;
        }
    }
}
