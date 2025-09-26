package org.example;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class QuickSortTest {
    @Test
    public void testQuickSortFixedInput() {
        int[] input = {10, -3, 7, 7, 0};
        int[] expected = {-3, 0, 7, 7, 10};

        Metrics m = new Metrics();
        QuickSort.sort(input, m);

        assertArrayEquals(expected, input);
    }

    @Test
    public void testQuickSortLargeArray() {
        Random rnd = new Random(456);
        int[] input = rnd.ints(10_000, -1_000_000, 1_000_000).toArray();
        int[] expected = input.clone();
        Arrays.sort(expected);

        Metrics m = new Metrics();
        QuickSort.sort(input, m);

        assertArrayEquals(expected, input, "QuickSort failed on large array");
    }
}
