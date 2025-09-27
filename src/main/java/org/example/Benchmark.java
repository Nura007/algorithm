package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Benchmark {

    private static final int[] SIZES = {100, 500, 1000, 5000, 10000};
    private static final int REPEAT = 5;

    private static final Random rand = new Random();

    public static void main(String[] args) {
        try (FileWriter out = new FileWriter("benchmark_results.csv")) {
            out.write("Algorithm,Size,Time(ms),Comparisons,Allocations,MaxDepth\n");

            for (int size : SIZES) {
                int[] baseArray = randArray(size);

                // MergeSort
                runBenchmark("MergeSort", baseArray, out, (arr, m) -> MergeSort.sort(arr, m));

                // QuickSort
                runBenchmark("QuickSort", baseArray, out, (arr, m) -> QuickSort.sort(arr, m));

                // Deterministic Select (ищем k-й элемент, k = n/2)
                runBenchmark("DeterministicSelect", baseArray, out,
                        (arr, m) -> DeterministicSelect.select(arr, arr.length / 2, m));

                // Closest Pair
                runBenchmark("ClosestPair", baseArray, out,
                        (arr, m) -> {
                            ClosestPair.Point[] pts = new ClosestPair.Point[arr.length];
                            for (int i = 0; i < arr.length; i++) {
                                pts[i] = new ClosestPair.Point(arr[i], rand.nextInt(10000));
                            }
                            ClosestPair.closestPair(pts, m);
                        });
            }

            System.out.println("✅ Benchmark finished. Results saved to benchmark_results.csv");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void runBenchmark(String name, int[] baseArray, FileWriter out,
                                     AlgorithmRunner runner) throws IOException {
        long totalTime = 0;
        long totalComparisons = 0;
        long totalAllocations = 0;
        long totalDepth = 0;

        for (int r = 0; r < REPEAT; r++) {
            int[] copy = baseArray.clone();
            Metrics m = new Metrics();
            long start = System.nanoTime();
            runner.run(copy, m);
            long end = System.nanoTime();

            totalTime += (end - start) / 1_000_000; // в миллисекундах
            totalComparisons += m.getComparisons();
            totalAllocations += m.getAllocations();
            totalDepth += m.getMaxDepth();
        }

        long avgTime = totalTime / REPEAT;
        long avgComparisons = totalComparisons / REPEAT;
        long avgAllocations = totalAllocations / REPEAT;
        long avgDepth = totalDepth / REPEAT;

        out.write(name + "," + baseArray.length + "," + avgTime + "," +
                avgComparisons + "," + avgAllocations + "," + avgDepth + "\n");
    }

    private static int[] randArray(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = rand.nextInt(1000000);
        return arr;
    }

    @FunctionalInterface
    interface AlgorithmRunner {
        void run(int[] arr, Metrics m);
    }
}
