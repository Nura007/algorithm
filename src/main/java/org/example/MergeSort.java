package org.example;

public class MergeSort {

    public static void sort(int[] arr, Metrics m) {
        if (arr == null || arr.length <= 1) return;
        int[] temp = new int[arr.length];
        m.incrementAllocations(); // выделили вспомогательный массив
        mergeSort(arr, temp, 0, arr.length - 1, m, 1);
    }

    private static void mergeSort(int[] arr, int[] temp, int left, int right, Metrics m, int depth) {
        if (left >= right) return;

        int mid = (left + right) / 2;
        mergeSort(arr, temp, left, mid, m, depth + 1);
        mergeSort(arr, temp, mid + 1, right, m, depth + 1);
        merge(arr, temp, left, mid, right, m);

        m.updateMaxDepth(depth);
    }

    private static void merge(int[] arr, int[] temp, int left, int mid, int right, Metrics m) {
        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            m.incrementComparisons();
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        while (j <= right) {
            temp[k++] = arr[j++];
        }

        for (i = left; i <= right; i++) {
            arr[i] = temp[i];
        }
    }
}
