package edu.hw10.task1;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomObjectGenerator {
    private static final int LOWER_BOUND = 32;
    private static final int UPPER_BOUND = 126;
    private static final double NULL_PROBABILITY = 0.1;
    private static final int WORD_LENGTH = 5;
    private final Random random;

    public RandomObjectGenerator(long seed) {
        this.random = new Random(seed);
    }

    public RandomObjectGenerator() {
        this.random = new Random();
    }

    public <T> T nextObject(Class<T> clazz, String factory)
        throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Method> factoryMethods = Arrays.stream(clazz.getDeclaredMethods())
            .filter(m -> m.getName().equals(factory))
            .toList();
        if (factoryMethods.isEmpty()) {
            throw new RuntimeException();
        }
        Method method = factoryMethods.get(0);

        Parameter[] parameters = method.getParameters();
        Object[] arguments = new Object[parameters.length];

        for (int i = 0; i < parameters.length; i++) {
            arguments[i] = generateRandomObject(parameters[i]);
        }

        return (T) method.invoke(null, arguments);
    }

    public <T> T nextObject(Class<T> clazz)
        throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<T> constructor = (Constructor<T>) clazz.getDeclaredConstructors()[0];
        Parameter[] parameters = constructor.getParameters();
        Object[] arguments = new Object[parameters.length];

        for (int i = 0; i < parameters.length; i++) {
            arguments[i] = generateRandomObject(parameters[i]);
        }

        return constructor.newInstance(arguments);
    }

    @SuppressWarnings({"CyclomaticComplexity", "ReturnCount"})
    public Object generateRandomObject(Parameter parameter) {
        var type = parameter.getType();

        if (type.equals(int.class) || type.equals(Integer.class)) {
            var minAnnotation = parameter.getAnnotation(Min.class);
            var maxAnnotation = parameter.getAnnotation(Max.class);
            int min =
                (minAnnotation != null && Integer.MIN_VALUE < minAnnotation.value())
                    ? (int) minAnnotation.value() : Integer.MIN_VALUE;
            int max =
                (maxAnnotation != null && Integer.MAX_VALUE > maxAnnotation.value())
                    ? (int) maxAnnotation.value() : Integer.MAX_VALUE;
            if (max <= min) {
                return null;
            }

            return random.nextInt(min, max);

        } else if (type.equals(long.class) || type.equals(Long.class)) {
            var minAnnotation = parameter.getAnnotation(Min.class);
            var maxAnnotation = parameter.getAnnotation(Max.class);
            long min = minAnnotation != null ? minAnnotation.value() : Long.MIN_VALUE;
            long max = maxAnnotation != null ? maxAnnotation.value() : Long.MAX_VALUE;
            if (max <= min) {
                return null;
            }

            return random.nextLong(min, max);

        } else if (type.equals(String.class)) {
            if (parameter.getAnnotation(NotNull.class) == null && random.nextDouble() < NULL_PROBABILITY) {
                return null;
            }
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < WORD_LENGTH; i++) {
                result.append((char) random.nextInt(LOWER_BOUND, UPPER_BOUND));
            }
            return result.toString();
        }
        return null;
    }
}


