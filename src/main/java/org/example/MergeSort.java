package org.example;

import java.util.Arrays;

public class MergeSort {
    public static void sort(int[] arr, Metrics m) {
        int[] buf = new int[arr.length];
        m.allocate();
        sort(arr, 0, arr.length - 1, buf, m);
    }

    private static void sort(int[] arr, int l, int r, int[] buf, Metrics m) {
        if (l >= r) return;
        m.enter();
        int mid = (l + r) / 2;
        sort(arr, l, mid, buf, m);
        sort(arr, mid + 1, r, buf, m);
        merge(arr, l, mid, r, buf, m);
        m.exit();
    }

    private static void merge(int[] arr, int l, int mid, int r, int[] buf, Metrics m) {
        int i = l, j = mid + 1, k = l;
        while (i <= mid && j <= r) {
            m.compare();
            if (arr[i] <= arr[j]) buf[k++] = arr[i++];
            else buf[k++] = arr[j++];
        }
        while (i <= mid) buf[k++] = arr[i++];
        while (j <= r) buf[k++] = arr[j++];
        for (int x = l; x <= r; x++) arr[x] = buf[x];
    }

    public static void main(String[] args) {
        int[] arr = {5, 3, 8, 1, 2};
        Metrics m = new Metrics();
        MergeSort.sort(arr, m);
        System.out.println("MergeSort result: " + Arrays.toString(arr));
        System.out.println("Comparisons=" + m.comparisons.get() +
                ", Allocations=" + m.allocations.get() +
                ", MaxDepth=" + m.getMaxDepth());
    }
}
