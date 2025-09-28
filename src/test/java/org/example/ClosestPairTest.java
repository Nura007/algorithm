package org.example;

import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class ClosestPairTest {

    @Test
    public void testClosestPairFixedPoints() {
        ClosestPair.Point[] pts = {
                new ClosestPair.Point(0, 0),
                new ClosestPair.Point(3, 4),
                new ClosestPair.Point(5, 1),
                new ClosestPair.Point(1, 1)
        };

        Metrics m = new Metrics();
        double result = ClosestPair.closestPair(pts, m);
        double expected = Math.sqrt(2);

        assertEquals(expected, result, 1e-9, "Closest distance should match expected sqrt(2)");
    }

    @Test
    public void testClosestPairLargeSet() {
        Random rnd = new Random(999);
        ClosestPair.Point[] pts = new ClosestPair.Point[2000];
        for (int i = 0; i < pts.length; i++) {
            pts[i] = new ClosestPair.Point(rnd.nextInt(1000), rnd.nextInt(1000));
        }

        Metrics m = new Metrics();
        double d = ClosestPair.closestPair(pts, m);

        assertTrue(d >= 0, "Distance should be non-negative");
        assertTrue(Double.isFinite(d), "Distance should be finite");
    }
}
