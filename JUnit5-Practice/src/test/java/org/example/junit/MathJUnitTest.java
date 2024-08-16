package org.example.junit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MathJUnitTest {
    private MathJUnit mathJUnit;


    @BeforeEach
    void initEach() {
        mathJUnit = new MathJUnit();
    }

    @Nested
    class AddTest {
        @Test
        void testAddingTwoPositives() {
            assertEquals(2, mathJUnit.add(1, 1),
                    "Add method should return the sum of two numbers");
        }

        @Test
        void testAddingTwoNegatives() {
            assertEquals(-2, mathJUnit.add(-1, -1),
                    "Add method should return the sum of two numbers");
        }

        @Test
        void testAddingAPositiveAndANegative() {
            assertEquals(0, mathJUnit.add(-1, 1),
                    "Add method should return the sum of two numbers");
        }
    }

    @Test
    void testSubtract() {
        assertAll(
                () -> assertEquals(1, mathJUnit.subtract(2,1)),
                () -> assertEquals(-1, mathJUnit.subtract(1,2)),
                () -> assertEquals(3, mathJUnit.subtract(2,-1))
        );
    }

    @Test
    void testMultiply() {
        assertAll(
                () -> assertEquals(0, mathJUnit.multiply(1, 0)),
                () -> assertEquals(1, mathJUnit.multiply(1, 1)),
                () -> assertEquals(6, mathJUnit.multiply(2, 3))
        );
    }

    @Test
    void computeCircleArea() {
        assertEquals(314.1592653589793, mathJUnit.computeCircleArea(10),
                "Should return right circle area");
    }

    @Test
    void testDivide() {
        assertThrows(ArithmeticException.class, () -> mathJUnit.divide(1, 0),
                "Divide should throw ArithmeticException when denominator is zero");
    }
}