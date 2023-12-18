package edu.hw10.task2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CacheProxy implements InvocationHandler {
    private static final String CACHE_DIRECTORY = "./cache/";
    private final Object target;
    private final Map<String, Object> cache = new HashMap<>();

    private CacheProxy(Object target) {
        this.target = target;
    }

    public static <T> T create(Object target, Class<T> interfaceClass) {
        return (T) Proxy.newProxyInstance(
            interfaceClass.getClassLoader(),
            new Class<?>[] {interfaceClass},
            new CacheProxy(target)
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Cache cacheAnnotation = method.getAnnotation(Cache.class);
        if (cacheAnnotation != null) {
            String key = method.getName() + Arrays.toString(args);

            if (cache.containsKey(key)) {
                return cache.get(key);
            }

            Object result;
            boolean isPersist = cacheAnnotation.persist();

            if (isPersist && (result = loadFromDisk(key)) != null) {
                return result;
            }

            result = method.invoke(target, args);
            cache.put(key, result);

            if (isPersist) {
                saveToDisk(key, result);
            }

            return result;
        } else {
            return method.invoke(target, args);
        }
    }

    private void saveToDisk(String key, Object result) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CACHE_DIRECTORY + key))) {
            oos.writeObject(result);
        }
    }

    private Object loadFromDisk(String key) throws IOException, ClassNotFoundException {
        File file = new File(CACHE_DIRECTORY + key);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                return ois.readObject();
            }
        }
        return null;
    }
}
