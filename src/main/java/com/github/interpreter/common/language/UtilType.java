package com.github.interpreter.common.language;

import com.github.interpreter.token.type.GenericType;
import org.jetbrains.annotations.Nullable;

import java.util.regex.Pattern;

public class UtilType {

    public static final Pattern INTEGER = Pattern.compile("^\\d+$");
    public static final Pattern LONG = Pattern.compile("^\\d+([l]|[L]$)");
    public static final Pattern FLOAT = Pattern.compile("^\\d+\\.\\d+([f]|[F]$)");
    public static final Pattern DOUBLE = Pattern.compile("^\\d+\\.\\d+([d]|[D]$)?");

    @Nullable
    public static GenericType getType(String value) {
        if (INTEGER.matcher(value).matches()) {
            return GenericType.INTEGER;
        } else if (LONG.matcher(value).matches()) {
            return GenericType.LONG;
        } else if (FLOAT.matcher(value).matches()) {
            return GenericType.FLOAT;
        } else if (DOUBLE.matcher(value).matches()) {
            return GenericType.DOUBLE;
        }

        return null;
    }
}
