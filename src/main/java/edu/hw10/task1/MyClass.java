package edu.hw10.task1;

public class MyClass {
    private int first;
    private int second;
    private int third;
    private String string;

    public MyClass(@Min(0) int first, @Min(-100) @Max(0) int second, int third, @NotNull String string) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.string = string;
    }

    public static MyClass create(@Min(0) int first, @Min(-100) @Max(0) int second, int third, @NotNull String string) {
        return new MyClass(first, second, third, string);
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int getThird() {
        return third;
    }

    public void setThird(int third) {
        this.third = third;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
