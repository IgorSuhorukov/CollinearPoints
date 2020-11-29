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

        BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(new Point[] {point1, point2, point3});
        assertEquals(0, bruteCollinearPoints.numberOfSegments());

        bruteCollinearPoints = new BruteCollinearPoints(new Point[] {point1, point2, point3, point4});
        assertEquals(1, bruteCollinearPoints.numberOfSegments());
        assertEquals((new LineSegment(point1, point4)).toString(), bruteCollinearPoints.segments()[0].toString());
    }

    @Test
    public void testFindLineSegmentsCase2() {
        Point point1 = new Point(1, 1);
        Point point2 = new Point(1, 1);
        Point point3 = new Point(1, 1);
        Point point4 = new Point(1, 1);

        BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(new Point[] {point1, point2, point3});
        assertEquals(0, bruteCollinearPoints.numberOfSegments());

        bruteCollinearPoints = new BruteCollinearPoints(new Point[] {point1, point2, point3, point4});
        assertEquals(0, bruteCollinearPoints.numberOfSegments());
        assertNull(bruteCollinearPoints.segments()[0]);
    }
}
