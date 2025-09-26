package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Benchmark {

    private static final Random rnd = new Random();

    public static void main(String[] args) {
        int[] sizes = {100, 500, 1000, 5000, 10000};

        try (FileWriter out = new FileWriter("benchmark_results.csv")) {
            out.write("Algorithm,Size,Time(ms),Comparisons,Allocations,MaxDepth\n");

            for (int n : sizes) {
                int[] base = rnd.ints(n, -1_000_000, 1_000_000).toArray();

                // MergeSort
                runAndSave("MergeSort", base.clone(), out, (arr, m) -> MergeSort.sort(arr, m));

                // QuickSort
                runAndSave("QuickSort", base.clone(), out, (arr, m) -> QuickSort.sort(arr, m));

                // Select
                runAndSave("DeterministicSelect", base.clone(), out,
                        (arr, m) -> DeterministicSelect.select(arr, n / 2, m));

                // ClosestPair
                ClosestPair.Point[] pts = new ClosestPair.Point[n];
                for (int i = 0; i < n; i++) {
                    pts[i] = new ClosestPair.Point(rnd.nextDouble() * 1000, rnd.nextDouble() * 1000);
                }
                long start = System.nanoTime();
                Metrics m = new Metrics();
                double d = ClosestPair.closest(pts, m);
                long time = (System.nanoTime() - start) / 1_000_000;
                out.write(String.format("ClosestPair,%d,%d,%d,%d,%d\n",
                        n, time, m.comparisons.get(), m.allocations.get(), m.getMaxDepth()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Benchmark finished! Results saved in benchmark_results.csv");
    }


    private static void runAndSave(String name, int[] arr, FileWriter out,
                                   AlgorithmRunner runner) throws IOException {
        Metrics m = new Metrics();
        long start = System.nanoTime();
        runner.run(arr, m);
        long time = (System.nanoTime() - start) / 1_000_000;

        out.write(String.format("%s,%d,%d,%d,%d,%d\n",
                name, arr.length, time, m.comparisons.get(), m.allocations.get(), m.getMaxDepth()));
    }

    @FunctionalInterface
    private interface AlgorithmRunner {
        void run(int[] arr, Metrics m);
    }
}
