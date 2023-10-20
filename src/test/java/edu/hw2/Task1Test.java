package edu.hw2;

import edu.hw2.task1.Expr;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    @DisplayName("Test Constant expression")
    void testConstant() {
        var constant = new Expr.Constant(1);

        assertThat(constant.evaluate()).isEqualTo(1);
    }

    @Test
    @DisplayName("Test Negate expression")
    void testNegate() {
        var constant = new Expr.Constant(1);
        var negate = new Expr.Negate(constant);

        assertThat(negate.evaluate()).isEqualTo(-1);
    }
    @Test
    @DisplayName("Test Exponent expression")
    void testExponent() {
        var constant = new Expr.Constant(2);
        var exp = new Expr.Exponent(constant, 3);

        assertThat(exp.evaluate()).isEqualTo(8);
    }
    @Test
    @DisplayName("Test Addition expression")
    void testAddition() {
        var constant1 = new Expr.Constant(1);
        var constant2 = new Expr.Constant(2);
        var addition = new Expr.Addition(constant1, constant2);

        assertThat(addition.evaluate()).isEqualTo(3);
    }
    @Test
    @DisplayName("Test Multiplication expression")
    void testMultiplication() {
        var constant1 = new Expr.Constant(1);
        var constant2 = new Expr.Constant(2);
        var addition = new Expr.Multiplication(constant1, constant2);

        assertThat(addition.evaluate()).isEqualTo(2);
    }
    @Test
    @DisplayName("Test expression composition")
    void testExpressions1() {
        Expr two = new Expr.Constant(2);
        Expr four = new Expr.Constant(4);
        Expr negOne = new Expr.Negate(new Expr.Constant(1));
        Expr sumTwoFour = new Expr.Addition(two, four);
        Expr mult = new Expr.Multiplication(sumTwoFour, negOne);
        Expr exp = new Expr.Exponent(mult, 2);
        Expr res = new Expr.Addition(exp, new Expr.Constant(1));

        assertThat(res.evaluate()).isEqualTo(37);
    }

    @Test
    @DisplayName("Test expression composition")
    void testExpressions2() {
        Expr two = new Expr.Constant(2);
        Expr three = new Expr.Constant(3);
        Expr four = new Expr.Constant(4);
        Expr one = new Expr.Constant(1);
        Expr five = new Expr.Constant(5);

        Expr sumTwoThree = new Expr.Addition(two, three);
        Expr subFourOne = new Expr.Addition(four, new Expr.Negate(one));
        Expr mult = new Expr.Multiplication(sumTwoThree, subFourOne);
        Expr res = new Expr.Multiplication(mult, new Expr.Exponent(five, 2));

        assertThat(res.evaluate()).isEqualTo(375.0);
    }
}
