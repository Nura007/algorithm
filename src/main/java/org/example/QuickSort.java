package org.example;

import java.util.Random;

public class QuickSort {

    private static final int INSERTION_SORT_THRESHOLD = 20;
    private static final Random rand = new Random();

    public static void sort(int[] arr, Metrics m) {
        quickSort(arr, 0, arr.length - 1, m, 1);
    }

    private static void quickSort(int[] arr, int low, int high, Metrics m, int depth) {
        if (low < high) {

            if (high - low + 1 <= INSERTION_SORT_THRESHOLD) {
                insertionSort(arr, low, high, m);
                return;
            }

            int pivotIndex = medianOfThree(arr, low, high, m);
            int pivot = arr[pivotIndex];

            // Partition
            int i = low, j = high;
            while (i <= j) {
                while (arr[i] < pivot) { m.incrementComparisons(); i++; }
                while (arr[j] > pivot) { m.incrementComparisons(); j--; }
                if (i <= j) {
                    swap(arr, i, j, m);
                    i++; j--;
                }
            }

            m.updateMaxDepth(depth);
            if (low < j) quickSort(arr, low, j, m, depth + 1);
            if (i < high) quickSort(arr, i, high, m, depth + 1);
        }
    }

    private static void insertionSort(int[] arr, int low, int high, Metrics m) {
        for (int i = low + 1; i <= high; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= low && arr[j] > key) {
                m.incrementComparisons();
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    // Median-of-three pivot selection
    private static int medianOfThree(int[] arr, int low, int high, Metrics m) {
        int mid = low + (high - low) / 2;
        if (arr[low] > arr[mid]) swap(arr, low, mid, m);
        if (arr[low] > arr[high]) swap(arr, low, high, m);
        if (arr[mid] > arr[high]) swap(arr, mid, high, m);
        return mid;
    }

    private static void swap(int[] arr, int i, int j, Metrics m) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
        m.incrementAllocations();
    }

    public static void main(String[] args) {
        int[] arr = {5, 2, 9, 1, 5, 6};
        Metrics m = new Metrics();
        sort(arr, m);

        System.out.println("Sorted:");
        for (int n : arr) System.out.print(n + " ");
        System.out.println("\n" + m);
    }
}
