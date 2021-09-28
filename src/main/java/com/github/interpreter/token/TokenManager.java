package com.github.interpreter.token;

import com.github.interpreter.validation.syntax.exception.UnknownSyntaxException;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class TokenManager {

    private Queue<Tokenizer> tokenizersQueque = new LinkedBlockingQueue<>();

    public TokenManager() {
    }

    public Tokenizer tokenizeScript(String script) {
        Tokenizer tokenizer = new Tokenizer(script);
        if (tokenizer.tokenize().size() < 1) {
            throw new UnknownSyntaxException("Script doesn't contains any code that can be processed.");
        }

        return tokenizer;
    }

    public Queue<Tokenizer> getTokenizersQueque() {
        return tokenizersQueque;
    }
}
