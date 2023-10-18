package edu.hw2.task4;

public class Task4 {
    private Task4() {
    }

    public static CollingInfo getCollingInfo() {
        var stackTrace = Thread.currentThread().getStackTrace();

        if (stackTrace.length > 0) {
            var currentStackFrame = stackTrace[stackTrace.length - 1];

            return new CollingInfo(currentStackFrame.getClassName(), currentStackFrame.getMethodName());
        }
        throw new RuntimeException("Stack is empty.");
    }

    public record CollingInfo(String className, String methodName) {
    }
}
