package com.skillbox.fibonacci;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class FibonacciServiceTest {

    private final FibonacciRepository repository = Mockito.mock(FibonacciRepository.class);
    private final FibonacciCalculator calculator = new FibonacciCalculator();

    private final FibonacciService service = new FibonacciService(repository, calculator);

    @Test
    @DisplayName("Test fibonacciNumber if exists in repository")
    public void testFibonacciNumberIfInRepository() {
        int fibonacciIndex = 10;
        int fibonacciAnswer = 55;
        FibonacciNumber mockFibonacciNumber = new FibonacciNumber(fibonacciIndex, fibonacciAnswer);
        when(repository.findByIndex(fibonacciIndex)).thenReturn(Optional.of(mockFibonacciNumber));
        FibonacciNumber fibonacciNumber = service.fibonacciNumber(fibonacciIndex);
        assertEquals(fibonacciAnswer, fibonacciNumber.getValue());
        verify(repository, times(1)).findByIndex(fibonacciIndex);
    }

    @Test
    @DisplayName("Test fibonacciNumber if NOT exists in repository")
    public void testFibonacciNumberIfNotInRepository() {
        int fibonacciIndex = 10;
        int fibonacciAnswer = 55;
        FibonacciNumber fibonacciNumber = service.fibonacciNumber(fibonacciIndex);
        assertEquals(fibonacciAnswer, fibonacciNumber.getValue());
        verify(repository, times(1));


    }

    @Test
    @DisplayName("Test fibonacciNumber if index < 1")
    public void testFibonacciNumberIfLessThenOne() {
        int fibonacciIndex = 0;
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> service.fibonacciNumber(fibonacciIndex));
        String expectedMessage = "Index should be greater or equal to 1";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.equals(expectedMessage));
    }

}
