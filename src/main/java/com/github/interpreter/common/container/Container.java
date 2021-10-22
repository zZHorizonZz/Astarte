package com.github.interpreter.common.container;

import java.util.HashMap;
import java.util.Map;

public class Container<T> {

    private final Map<String, T> valueMap = new HashMap<>();

    public T get(String name) {
        return valueMap.get(name);
    }

    public void add(String name, T value) {
        valueMap.put(name, value);
    }

    public boolean contains(String name) {
        return valueMap.containsKey(name);
    }

    public int size() {
        return valueMap.size();
    }

    public Map<String, T> getValueMap() {
        return valueMap;
    }
}
