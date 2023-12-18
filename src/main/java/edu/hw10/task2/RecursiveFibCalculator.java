package edu.hw10.task2;

public class RecursiveFibCalculator implements FibCalculator {
    @Override
    public long fib(int number) {
        if (number == 0 || number == 1) {
            return 1;
        }
        return fib(number - 1) + fib(number - 2);
    }
}
