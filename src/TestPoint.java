import org.junit.Test;

import static org.junit.Assert.*;

public class TestPoint {
    private static final double DELTA = 0.001;

    @Test
    public void testSlopeTo() {
        int x0 = 3;
        int y0 = 12;
        int x1 = 17;
        int y1 = 8;

        Point point = new Point(x0, y0);
        Point point2 = new Point(x1, y1);
        double result = point.slopeTo(point2);

        double expected = (double) (y1 - y0) / (x1 - x0);

        assertEquals(expected, result, DELTA);
    }

    @Test
    public void testToString() {
        Point point = new Point(1, 2);
        assertEquals("(1, 2)", point.toString());

        point = new Point(0, 0);
        assertEquals("(0, 0)", point.toString());

        point = new Point(13874, 32265);
        assertEquals("(13874, 32265)", point.toString());
    }

    @Test
    public void testSlopeToWhenVerticalLine() {
        int x0 = 3;
        int y0 = 12;
        int x1 = 3;
        int y1 = 22;

        Point point = new Point(x0, y0);
        Point point2 = new Point(x1, y1);
        double result = point.slopeTo(point2);

        assertEquals(Double.POSITIVE_INFINITY, result, DELTA);
    }

    @Test
    public void testSlopeToWhenHorizontalLine() {
        int x0 = 3;
        int y0 = 8;
        int x1 = 17;
        int y1 = 8;

        Point point = new Point(x0, y0);
        Point point2 = new Point(x1, y1);
        double result = point.slopeTo(point2);

        assertEquals(-0.0f, result, DELTA);
    }

    @Test
    public void testSlopeToWhenDegenerateLine() {
        int x0 = 17;
        int y0 = 8;
        int x1 = 17;
        int y1 = 8;

        Point point = new Point(x0, y0);
        Point point2 = new Point(x1, y1);
        double result = point.slopeTo(point2);

        assertEquals(Double.NEGATIVE_INFINITY, result, DELTA);
    }

    @Test
    public void testCompareTo() {
        Point point;
        Point point2;

        point = new Point(0, 0);
        point2 = new Point(1, 1);
        assertEquals(-1, point.compareTo(point2));

        point = new Point(0, 0);
        point2 = new Point(0, 0);
        assertEquals(0, point.compareTo(point2));

        point = new Point(1, 1);
        point2 = new Point(0, 0);
        assertEquals(1, point.compareTo(point2));

        point = new Point(10, 3);
        point2 = new Point(10, 7);
        assertEquals(-1, point.compareTo(point2));

        point = new Point(10, 4);
        point2 = new Point(10, 2);
        assertEquals(1, point.compareTo(point2));

        point = new Point(3, 3);
        point2 = new Point(5, 3);
        assertEquals(-1, point.compareTo(point2));

        point = new Point(12, 3);
        point2 = new Point(6, 3);
        assertEquals(1, point.compareTo(point2));
    }

    @Test
    public void testSlopeOrder() {
        Point point;
        Point point1;
        Point point2;
        int result;

        point = new Point(0, 0);
        point1 = new Point(5, 7);
        point2 = new Point(9, 3);
        result = point.slopeOrder().compare(point1, point2);
        assertEquals(-1, result);

        point = new Point(0, 0);
        point1 = new Point(0, 0);
        point2 = new Point(0, 0);
        result = point.slopeOrder().compare(point1, point2);
        assertEquals(0, result);

        point = new Point(0, 0);
        point1 = new Point(9, 3);
        point2 = new Point(9, 3);
        result = point.slopeOrder().compare(point1, point2);
        assertEquals(0, result);

        point = new Point(0, 0);
        point1 = new Point(3, 1);
        point2 = new Point(9, 3);
        result = point.slopeOrder().compare(point1, point2);
        assertEquals(0, result);

        point = new Point(0, 0);
        point1 = new Point(12, 3);
        point2 = new Point(12, 3);
        result = point.slopeOrder().compare(point1, point2);
        assertEquals(0, result);
    }
}
