package edu.hw2.task1;

public sealed interface Expr {
    double evaluate();

    record Constant(double x) implements Expr {
        @Override
        public double evaluate() {
            return x;
        }

        @Override
        public String toString() {
            return String.valueOf(x);
        }
    }

    record Negate(Expr expr) implements Expr {
        @Override
        public double evaluate() {
            return -expr.evaluate();
        }

        @Override
        public String toString() {
            return "(-" + expr.toString() + ")";
        }
    }

    record Exponent(Expr base, double power) implements Expr {
        @Override
        public double evaluate() {
            return Math.pow(base.evaluate(), power);
        }

        @Override
        public String toString() {
            return base.toString() + "^" + power;
        }
    }

    record Addition(Expr expr1, Expr expr2) implements Expr {
        @Override
        public double evaluate() {
            return expr1.evaluate() + expr2.evaluate();
        }

        @Override
        public String toString() {
            return "(" + expr1.toString() + " + " + expr2.toString() + ")";
        }
    }

    record Multiplication(Expr expr1, Expr expr2) implements Expr {
        @Override
        public double evaluate() {
            return expr1.evaluate() * expr2.evaluate();
        }

        @Override
        public String toString() {
            return "(" + expr1.toString() + " * " + expr2.toString() + ")";
        }
    }
}
