package org.example;

public class ClosestPair {
    public static class Point {
        public double x, y;
        public Point(double x, double y) { this.x = x; this.y = y; }
    }

    public static double closest(Point[] pts, Metrics m) {
        double best = Double.POSITIVE_INFINITY;
        for (int i = 0; i < pts.length; i++) {
            for (int j = i + 1; j < pts.length; j++) {
                m.compare();
                double dx = pts[i].x - pts[j].x;
                double dy = pts[i].y - pts[j].y;
                best = Math.min(best, Math.hypot(dx, dy));
            }
        }
        return best;
    }

    public static void main(String[] args) {
        Point[] pts = {
                new Point(0, 0),
                new Point(3, 4),
                new Point(5, 1),
                new Point(1, 1)
        };
        Metrics m = new Metrics();
        double d = ClosestPair.closest(pts, m);
        System.out.println("ClosestPair result: " + d);
        System.out.println("Comparisons=" + m.comparisons.get());
    }
}
