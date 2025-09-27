package org.example;

import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {

    static class Point {
        int x, y;
        Point(int x, int y) { this.x = x; this.y = y; }
    }

    public static double closestPair(Point[] points, Metrics m) {
        Arrays.sort(points, Comparator.comparingInt(p -> p.x));
        return closestUtil(points, 0, points.length - 1, m, 1);
    }

    private static double closestUtil(Point[] pts, int left, int right, Metrics m, int depth) {
        if (right - left <= 3) {
            return bruteForce(pts, left, right, m);
        }

        int mid = (left + right) / 2;
        double dLeft = closestUtil(pts, left, mid, m, depth + 1);
        double dRight = closestUtil(pts, mid + 1, right, m, depth + 1);
        double d = Math.min(dLeft, dRight);

        Point midPoint = pts[mid];
        Point[] strip = Arrays.stream(pts, left, right + 1)
                .filter(p -> Math.abs(p.x - midPoint.x) < d)
                .toArray(Point[]::new);

        Arrays.sort(strip, Comparator.comparingInt(p -> p.y));

        m.updateMaxDepth(depth);

        return Math.min(d, stripClosest(strip, d, m));
    }

    private static double bruteForce(Point[] pts, int left, int right, Metrics m) {
        double min = Double.MAX_VALUE;
        for (int i = left; i <= right; i++) {
            for (int j = i + 1; j <= right; j++) {
                m.incrementComparisons();
                min = Math.min(min, dist(pts[i], pts[j]));
            }
        }
        return min;
    }

    private static double stripClosest(Point[] strip, double d, Metrics m) {
        double min = d;
        for (int i = 0; i < strip.length; ++i) {
            for (int j = i + 1; j < strip.length && (strip[j].y - strip[i].y) < min; ++j) {
                m.incrementComparisons();
                min = Math.min(min, dist(strip[i], strip[j]));
            }
        }
        return min;
    }

    private static double dist(Point p1, Point p2) {
        return Math.sqrt((long)(p1.x - p2.x) * (p1.x - p2.x) + (long)(p1.y - p2.y) * (p1.y - p2.y));
    }

    public static void main(String[] args) {
        Point[] pts = { new Point(0, 0), new Point(3, 4), new Point(7, 7), new Point(10, 10) };
        Metrics m = new Metrics();
        double d = closestPair(pts, m);
        System.out.println("Closest distance: " + d);
        System.out.println(m);
    }
}
