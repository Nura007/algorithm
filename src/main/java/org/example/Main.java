package org.example;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        int[] original = {5, 3, 8, 1, 2};

        // ---------- MergeSort ----------
        int[] arr1 = original.clone();
        Metrics m1 = new Metrics();
        MergeSort.sort(arr1, m1);
        System.out.println("MergeSort result: " + Arrays.toString(arr1));
        System.out.println("  Comparisons=" + m1.comparisons.get() +
                ", Allocations=" + m1.allocations.get() +
                ", MaxDepth=" + m1.getMaxDepth());
        System.out.println();

        // ---------- QuickSort ----------
        int[] arr2 = original.clone();
        Metrics m2 = new Metrics();
        QuickSort.sort(arr2, m2);
        System.out.println("QuickSort result: " + Arrays.toString(arr2));
        System.out.println("  Comparisons=" + m2.comparisons.get() +
                ", Allocations=" + m2.allocations.get() +
                ", MaxDepth=" + m2.getMaxDepth());
        System.out.println();

        // ---------- Deterministic Select ----------
        int[] arr3 = original.clone();
        int k = 2;
        Metrics m3 = new Metrics();
        int sel = DeterministicSelect.select(arr3, k, m3);
        System.out.println("DeterministicSelect k=" + k + " → " + sel);
        System.out.println("  Comparisons=" + m3.comparisons.get() +
                ", Allocations=" + m3.allocations.get() +
                ", MaxDepth=" + m3.getMaxDepth());
        System.out.println();

        // ---------- Closest Pair ----------
        ClosestPair.Point[] pts = {
                new ClosestPair.Point(0, 0),
                new ClosestPair.Point(3, 4),
                new ClosestPair.Point(5, 1),
                new ClosestPair.Point(1, 1)
        };
        Metrics m4 = new Metrics();
        double d = ClosestPair.closest(pts, m4);
        System.out.println("ClosestPair distance: " + d);
        System.out.println("  Comparisons=" + m4.comparisons.get());
    }
}
