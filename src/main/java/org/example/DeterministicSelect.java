package org.example;

import java.util.Arrays;

public class DeterministicSelect {

    private static final int INSERTION_SORT_THRESHOLD = 20;

    public static int select(int[] arr, int k, Metrics m) {
        return select(arr, 0, arr.length - 1, k, m, 1);
    }

    private static int select(int[] arr, int left, int right, int k, Metrics m, int depth) {
        if (right - left + 1 <= INSERTION_SORT_THRESHOLD) {
            insertionSort(arr, left, right, m);
            return arr[left + k];
        }

        int n = right - left + 1;
        int numMedians = (n + 4) / 5;
        int[] medians = new int[numMedians];
        for (int i = 0; i < numMedians; i++) {
            int subLeft = left + i * 5;
            int subRight = Math.min(subLeft + 4, right);
            insertionSort(arr, subLeft, subRight, m);
            medians[i] = arr[subLeft + (subRight - subLeft) / 2];
        }

        int pivot = select(medians, 0, numMedians - 1, numMedians / 2, m, depth + 1);

        int pos = partition(arr, left, right, pivot, m);
        int len = pos - left;

        m.updateMaxDepth(depth);

        if (k < len) return select(arr, left, pos - 1, k, m, depth + 1);
        else if (k > len) return select(arr, pos + 1, right, k - len - 1, m, depth + 1);
        else return arr[pos];
    }

    private static int partition(int[] arr, int left, int right, int pivot, Metrics m) {
        int i = left, j = right;
        while (i <= j) {
            while (arr[i] < pivot) { m.incrementComparisons(); i++; }
            while (arr[j] > pivot) { m.incrementComparisons(); j--; }
            if (i <= j) {
                int tmp = arr[i]; arr[i] = arr[j]; arr[j] = tmp;
                m.incrementAllocations();
                i++; j--;
            }
        }
        return i - 1;
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

    public static void main(String[] args) {
        int[] arr = {7, 10, 4, 3, 20, 15};
        Metrics m = new Metrics();
        int k = 2;
        int result = select(arr, k, m);
        System.out.println(k + "-th smallest: " + result);
        System.out.println(m);
    }
}
