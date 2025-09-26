package org.example;

public class DeterministicSelect {
    public static int select(int[] arr, int k, Metrics m) {
        return select(arr, 0, arr.length - 1, k, m);
    }

    private static int select(int[] arr, int l, int r, int k, Metrics m) {
        if (l == r) return arr[l];
        m.enter();
        int pivot = arr[(l + r) / 2]; // simple pivot for demo
        int pivotIndex = partition(arr, l, r, pivot, m);
        int len = pivotIndex - l + 1;
        int res;
        if (k == len - 1) res = arr[pivotIndex];
        else if (k < len - 1) res = select(arr, l, pivotIndex - 1, k, m);
        else res = select(arr, pivotIndex + 1, r, k - len, m);
        m.exit();
        return res;
    }

    private static int partition(int[] arr, int l, int r, int pivot, Metrics m) {
        int i = l, j = r;
        while (i <= j) {
            while (i <= j && arr[i] < pivot) { m.compare(); i++; }
            while (i <= j && arr[j] > pivot) { m.compare(); j--; }
            if (i <= j) {
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++; j--;
            }
        }
        return i - 1;
    }

    public static void main(String[] args) {
        int[] arr = {9, 1, 4, 7, 3};
        int k = 2; // third smallest
        Metrics m = new Metrics();
        int result = DeterministicSelect.select(arr, k, m);
        System.out.println("Select result (k=" + k + "): " + result);
        System.out.println("Comparisons=" + m.comparisons.get() +
                ", Allocations=" + m.allocations.get() +
                ", MaxDepth=" + m.getMaxDepth());
    }
}
