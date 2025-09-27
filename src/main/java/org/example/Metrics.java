package org.example;

public class Metrics {
    private long comparisons = 0;
    private long allocations = 0;
    private int maxDepth = 0;

    public void incrementComparisons() {
        comparisons++;
    }

    public void incrementAllocations() {
        allocations++;
    }

    public void updateMaxDepth(int depth) {
        maxDepth = Math.max(maxDepth, depth);
    }

    // Геттеры для Benchmark
    public long getComparisons() {
        return comparisons;
    }

    public long getAllocations() {
        return allocations;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    @Override
    public String toString() {
        return "Comparisons=" + comparisons +
                ", Allocations=" + allocations +
                ", MaxDepth=" + maxDepth;
    }
}
