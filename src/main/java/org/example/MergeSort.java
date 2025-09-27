package org.example;

public class MergeSort {

    private static final int INSERTION_SORT_THRESHOLD = 20;

    public static void sort(int[] arr, Metrics m) {
        int n = arr.length;

        if (n <= INSERTION_SORT_THRESHOLD) {
            insertionSort(arr, 0, n - 1, m);
            return;
        }

        int[] aux = new int[n];
        for (int size = 1; size < n; size *= 2) {
            for (int left = 0; left < n - size; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min(left + 2 * size - 1, n - 1);
                merge(arr, aux, left, mid, right, m);
            }
        }
    }

    private static void merge(int[] arr, int[] aux, int left, int mid, int right, Metrics m) {
        System.arraycopy(arr, left, aux, left, right - left + 1);
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            m.incrementComparisons();
            if (aux[i] <= aux[j]) arr[k++] = aux[i++];
            else arr[k++] = aux[j++];
        }
        while (i <= mid) arr[k++] = aux[i++];
        while (j <= right) arr[k++] = aux[j++];
        m.incrementAllocations();
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
        int[] arr = {5, 2, 9, 1, 5, 6};
        Metrics m = new Metrics();
        sort(arr, m);
        for (int x : arr) System.out.print(x + " ");
        System.out.println("\n" + m);
    }
}
