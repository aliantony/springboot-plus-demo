package com.example.demo.java8;

import java.util.Objects;
import java.util.function.Function;

public interface LambdaExceptionDecorator {

   static <T, R> Function<T, R> apply(CheckedFunction<T, R> function, Function<Throwable, R> handler) {
        Objects.requireNonNull(function);
        return t -> {
            try {
                return function.apply(t);
            } catch (Throwable ex) {
                return handler.apply(ex);
            }
        };
    }

}
