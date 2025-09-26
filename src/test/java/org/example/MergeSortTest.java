package org.example;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class MergeSortTest {
    @Test
    public void testMergeSortFixedInput() {
        int[] input = {5, 3, 8, 1, 2};
        int[] expected = {1, 2, 3, 5, 8};

        Metrics m = new Metrics();
        MergeSort.sort(input, m);

        assertArrayEquals(expected, input);
    }

    @Test
    public void testMergeSortLargeArray() {
        Random rnd = new Random(123);
        int[] input = rnd.ints(10_000, -1_000_000, 1_000_000).toArray();
        int[] expected = input.clone();
        Arrays.sort(expected);

        Metrics m = new Metrics();
        MergeSort.sort(input, m);

        assertArrayEquals(expected, input, "MergeSort failed on large array");
    }
}
