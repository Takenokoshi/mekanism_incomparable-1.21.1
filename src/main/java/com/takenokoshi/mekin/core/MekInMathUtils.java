package com.takenokoshi.mekin.core;

import java.util.function.LongSupplier;

public class MekInMathUtils {
    public static LongSupplier multiplyClamped(LongSupplier supplier, long multiplier) {
        if (multiplier <= 0) {
            throw new IllegalArgumentException();
        }
        return () -> {
            long value = supplier.getAsLong();
            return value > Long.MAX_VALUE / multiplier
                    ? Long.MAX_VALUE
                    : value * multiplier;
        };
    }
}
