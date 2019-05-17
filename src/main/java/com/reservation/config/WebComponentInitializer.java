package com.reservation.config;

import com.reservation.servlet.RequestResolver;

public class WebComponentInitializer {

    private static WebComponentInitializer initializer;
    private final RequestResolver requestResolver;

    private WebComponentInitializer() {

        DataComponentInitializer dataComponentInitializer = DataComponentInitializer.getInstance();
        requestResolver = new RequestResolver(this);
    }

    public static WebComponentInitializer getInstance() {
        if (initializer == null) {
            synchronized (WebComponentInitializer.class) {
                if (initializer == null) {
                    initializer = new WebComponentInitializer();
                }
            }
        }

        return initializer;
    }

    public RequestResolver getRequestResolver() {
        return requestResolver;
    }
}