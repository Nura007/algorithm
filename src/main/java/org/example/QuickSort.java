package org.example;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {
    private static final Random R = new Random();

    public static void sort(int[] arr, Metrics m) {
        quicksort(arr, 0, arr.length - 1, m);
    }

    private static void quicksort(int[] arr, int l, int r, Metrics m) {
        while (l < r) {
            m.enter();
            int p = partition(arr, l, r, m);
            if (p - l < r - p) {
                quicksort(arr, l, p - 1, m);
                l = p + 1;
            } else {
                quicksort(arr, p + 1, r, m);
                r = p - 1;
            }
            m.exit();
        }
    }

    private static int partition(int[] arr, int l, int r, Metrics m) {
        int pivotIndex = l + R.nextInt(r - l + 1);
        int pivot = arr[pivotIndex];
        swap(arr, pivotIndex, r);
        int i = l - 1;
        for (int j = l; j < r; j++) {
            m.compare();
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, r);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {10, -3, 7, 7, 0};
        Metrics m = new Metrics();
        QuickSort.sort(arr, m);
        System.out.println("QuickSort result: " + Arrays.toString(arr));
        System.out.println("Comparisons=" + m.comparisons.get() +
                ", Allocations=" + m.allocations.get() +
                ", MaxDepth=" + m.getMaxDepth());
    }
}
