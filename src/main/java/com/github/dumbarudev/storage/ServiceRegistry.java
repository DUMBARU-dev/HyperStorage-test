package com.github.dumbarudev.storage;

import java.util.HashMap;
import java.util.Map;

public class ServiceRegistry {
    // Stores the Class as the key, and the active instance object as the value
    private static final Map<Class<?>, Object> services = new HashMap<>();

    // Register an instance against its class
    public static <T> void register(Class<T> clazz, T instance) {
        services.put(clazz, instance);
    }

    // Retrieve the instance using the class reference (fully static)
    @SuppressWarnings("unchecked")
    public static <T> T get(Class<T> clazz) {
        return (T) services.get(clazz);
    }
}