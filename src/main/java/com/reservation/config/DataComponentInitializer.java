package com.reservation.config;

public class DataComponentInitializer {

    private static DataComponentInitializer initializer;

    private DataComponentInitializer() {

    }

    public static DataComponentInitializer getInstance() {
        if (initializer == null) {
            synchronized (DataComponentInitializer.class) {
                if (initializer == null) {
                    initializer = new DataComponentInitializer();
                }
            }
        }

        return initializer;
    }
}
