import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestBruteCollinearPoints {
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorThrows() {
        new BruteCollinearPoints(null);
    }

    @Test
    public void testFindLineSegmentsCase1() {
        Point point1 = new Point(1, 1);
        Point point2 = new Point(2, 2);
        Point point3 = new Point(3, 3);
        Point point4 = new Point(4, 4);

        BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(new Point[]{point1, point2, point3});
        assertEquals(0, bruteCollinearPoints.numberOfSegments());

        bruteCollinearPoints = new BruteCollinearPoints(new Point[]{point1, point2, point3, point4});
        assertEquals(1, bruteCollinearPoints.numberOfSegments());
        assertEquals((new LineSegment(point1, point4)).toString(), bruteCollinearPoints.segments()[0].toString());
    }

    @Test
    public void testFindLineSegmentsCase2() {
        Point point1 = new Point(1, 1);
        Point point2 = new Point(1, 1);
        Point point3 = new Point(1, 1);
        Point point4 = new Point(1, 1);

        BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(new Point[]{point1, point2, point3});
        assertEquals(0, bruteCollinearPoints.numberOfSegments());

        bruteCollinearPoints = new BruteCollinearPoints(new Point[]{point1, point2, point3, point4});
        assertEquals(0, bruteCollinearPoints.numberOfSegments());
        assertNull(bruteCollinearPoints.segments()[0]);
    }

    @Test
    public void testFindLineSegmentsCase3() {
        Point point1 = new Point(1, 2);
        Point point2 = new Point(3, 4);
        Point point3 = new Point(5, 6);
        Point point4 = new Point(5, 6);

        BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(new Point[]{point1, point2, point3, point4});
        assertEquals(0, bruteCollinearPoints.numberOfSegments());
        assertNull(bruteCollinearPoints.segments()[0]);
    }

    @Test
    public void testFindLineSegmentsCase4() {
        Point point1 = new Point(1, 2);
        Point point2 = new Point(3, 4);
        Point point3 = new Point(3, 4);
        Point point4 = new Point(5, 6);

        BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(new Point[]{point1, point2, point3, point4});
        assertEquals(0, bruteCollinearPoints.numberOfSegments());
        assertNull(bruteCollinearPoints.segments()[0]);
    }

    @Test
    public void testFindLineSegmentsCase5() {
        Point point1 = new Point(1, 2);
        Point point2 = new Point(1, 2);
        Point point3 = new Point(3, 4);
        Point point4 = new Point(5, 6);

        BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(new Point[]{point1, point2, point3, point4});
        assertEquals(0, bruteCollinearPoints.numberOfSegments());
        assertNull(bruteCollinearPoints.segments()[0]);
    }

    @Test
    public void testFindLineSegmentsCase6() {
        Point point1 = new Point(1, 2);
        Point point2 = new Point(1, 2);
        Point point3 = new Point(5, 6);
        Point point4 = new Point(5, 6);

        BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(new Point[]{point1, point2, point3, point4});
        assertEquals(0, bruteCollinearPoints.numberOfSegments());
        assertNull(bruteCollinearPoints.segments()[0]);
    }

    @Test
    public void testFindLineSegmentsCase7() {
        Point point1 = new Point(1, 2);
        Point point2 = new Point(1, 2);
        Point point3 = new Point(5, 6);
        Point point4 = new Point(5, 6);
        Point point5 = new Point(3, 4);
        Point point6 = new Point(7, 8);

        BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(new Point[]{point1, point2, point3, point4, point5, point6});
        assertEquals(1, bruteCollinearPoints.numberOfSegments());
        assertEquals((new LineSegment(point1, point6)).toString(), bruteCollinearPoints.segments()[0].toString());
    }

    @Test
    public void testFindLineSegmentsCase8() {
        Point point1 = new Point(1, 1);
        Point point2 = new Point(2, 2);
        Point point3 = new Point(3, 3);
        Point point4 = new Point(4, 4);
        Point point5 = new Point(1, 3);
        Point point6 = new Point(2, 6);
        Point point7 = new Point(3, 9);
        Point point8 = new Point(4, 12);

        Point[] points = new Point[]{point1, point2, point3, point4, point5, point6, point7, point8};
        BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);
        assertEquals(2, bruteCollinearPoints.numberOfSegments());
        assertEquals((new LineSegment(point1, point4)).toString(), bruteCollinearPoints.segments()[0].toString());
        assertEquals((new LineSegment(point5, point8)).toString(), bruteCollinearPoints.segments()[1].toString());
    }
}
