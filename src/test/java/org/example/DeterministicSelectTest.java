package org.example;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class DeterministicSelectTest {
    @Test
    public void testSelectFixedInput() {
        int[] input = {9, 1, 4, 7, 3};
        int k = 2;
        int expected = 4;

        Metrics m = new Metrics();
        int result = DeterministicSelect.select(input, k, m);

        assertEquals(expected, result);
    }

    @Test
    public void testSelectLargeArray() {
        Random rnd = new Random(789);
        int[] input = rnd.ints(10_000, -500_000, 500_000).toArray();
        int[] sorted = input.clone();
        Arrays.sort(sorted);

        int k = 5000; // middle element
        Metrics m = new Metrics();
        int result = DeterministicSelect.select(input, k, m);

        assertEquals(sorted[k], result, "Select failed on large array");
    }
}
