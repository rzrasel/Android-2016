package com.rz.librarycore.http;

/**
 * Created by Rz Rasel on 2016-11-25.
 */

public enum HTTPMethod {
    GET("GET"), POST("POST"), DELETE("DELETE");
    private final String methodName;

    HTTPMethod(String argMethodName) {
        this.methodName = argMethodName;
    }

    public String getMethodName() {
        return this.methodName;
    }
}
