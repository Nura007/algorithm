package org.example;

import java.util.concurrent.atomic.AtomicInteger;

public class Metrics {
    public AtomicInteger comparisons = new AtomicInteger();
    public AtomicInteger allocations = new AtomicInteger();
    private int depth = 0;
    private int maxDepth = 0;

    public void compare() {
        comparisons.incrementAndGet();
    }

    public void allocate() {
        allocations.incrementAndGet();
    }

    public void enter() {
        depth++;
        if (depth > maxDepth) maxDepth = depth;
    }

    public void exit() {
        depth--;
    }

    public int getMaxDepth() {
        return maxDepth;
    }
}
