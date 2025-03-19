package com.skillbox.fibonacci;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class FibonacciCalculatorTest {

    FibonacciCalculator calculator;

    @BeforeEach
    public void setup() {
        calculator = new FibonacciCalculator();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -10})
    @DisplayName("test getFibonacciNumber if inserted < 1")
    public void testGetFibonacciNumberZero(int number) {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> calculator.getFibonacciNumber(number));

        String expectedMessage = "Index should be greater or equal to 1";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.equals(expectedMessage));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    @DisplayName("test getFibonacciNumber if inserted 1 or 2")
    public void testGetFibonacciNumberOneOrTwo(int number) {
        assertEquals(1, calculator.getFibonacciNumber(number));
    }

    @Test
    @DisplayName("test getFibonacciNumber if inserted 3")
    public void testGetFibonacciNumberThree() {
        assertEquals(2, calculator.getFibonacciNumber(3));
    }
    @Test
    @DisplayName("test getFibonacciNumber if inserted 10")
    public void testGetFibonacciNumberTen() {
        assertEquals(55, calculator.getFibonacciNumber(10));
    }
    @Test
    @DisplayName("test getFibonacciNumber if inserted 30")
    public void testGetFibonacciNumberThirty() {
        assertEquals(832040, calculator.getFibonacciNumber(30));
    }

}
