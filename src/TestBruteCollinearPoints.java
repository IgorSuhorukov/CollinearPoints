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

    @Test(expected = IllegalArgumentException.class)
    public void testFindLineSegmentsCase2() {
        Point point1 = new Point(1, 1);
        Point point2 = new Point(1, 1);
        Point point3 = new Point(1, 1);
        Point point4 = new Point(1, 1);

        new BruteCollinearPoints(new Point[]{point1, point2, point3, point4});
    }

    @Test
    public void testFindLineSegmentsCase3() {
        Point point1 = new Point(1, 2);
        Point point2 = new Point(3, 4);
        Point point3 = new Point(5, 6);

        BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(new Point[]{point1, point2, point3});
        assertEquals(0, bruteCollinearPoints.numberOfSegments());
        assertEquals(0, bruteCollinearPoints.segments().length);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindLineSegmentsCase4() {
        Point point1 = new Point(1, 2);
        Point point2 = new Point(3, 4);
        Point point3 = new Point(3, 4);
        Point point4 = new Point(5, 6);

        new BruteCollinearPoints(new Point[]{point1, point2, point3, point4});
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindLineSegmentsCase5() {
        Point point1 = new Point(1, 2);
        Point point2 = new Point(1, 2);
        Point point3 = new Point(3, 4);
        Point point4 = new Point(5, 6);

        new BruteCollinearPoints(new Point[]{point1, point2, point3, point4});
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindLineSegmentsCase6() {
        Point point1 = new Point(1, 2);
        Point point2 = new Point(1, 2);
        Point point3 = new Point(5, 6);
        Point point4 = new Point(5, 6);

        new BruteCollinearPoints(new Point[]{point1, point2, point3, point4});
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindLineSegmentsCase7() {
        Point point1 = new Point(1, 2);
        Point point2 = new Point(1, 2);
        Point point3 = new Point(5, 6);
        Point point4 = new Point(5, 6);
        Point point5 = new Point(3, 4);
        Point point6 = new Point(7, 8);

        new BruteCollinearPoints(new Point[]{point1, point2, point3, point4, point5, point6});
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

    @Test
    public void testFindLineSegmentsCase9() {
        Point[] points = new Point[]{
            new Point(20584,  7028),
            new Point(20584, 19008),
            new Point(20584, 19359),
            new Point(20584, 10953),
        };
        BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);
        assertEquals(1, bruteCollinearPoints.segments().length);
        assertEquals(1, bruteCollinearPoints.numberOfSegments());
        assertEquals("(20584, 7028) -> (20584, 19359)", bruteCollinearPoints.segments()[0].toString());
    }

    @Test
    public void testFindLineSegmentsCase10() {
        Point[] points = new Point[]{
                new Point(12490, 15460),
                new Point(8854, 16980),
                new Point(12490, 15688),
                new Point(17932,  2487),
                new Point(1846,  9294),
                new Point(11420, 18424),
                new Point(17932, 17387),
                new Point(8854, 16077),
                new Point(11420,  1845),
                new Point(17932, 18141),
                new Point(1846,  7819),
                new Point(8854,  3960),
                new Point(1846,  3568),
                new Point(11420,  2279),
                new Point(11420, 19875),
                new Point(12490,  8497),
                new Point(1846, 13958),
                new Point(8854,  6240),
                new Point(12490, 15226),
                new Point(17932,  3216)
        };

        BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);
        assertEquals(5, bruteCollinearPoints.segments().length);
        assertEquals("(12490, 8497) -> (12490, 15688)", bruteCollinearPoints.segments()[0].toString());
        assertEquals("(8854, 3960) -> (8854, 16980)", bruteCollinearPoints.segments()[1].toString());
        assertEquals("(17932, 2487) -> (17932, 18141)", bruteCollinearPoints.segments()[2].toString());
        assertEquals("(1846, 3568) -> (1846, 13958)", bruteCollinearPoints.segments()[3].toString());
        assertEquals("(11420, 1845) -> (11420, 19875)", bruteCollinearPoints.segments()[4].toString());
    }

    @Test
    public void testFindLineSegmentsCase12() {
        Point[] points = new Point[] {
                new Point(6, 5),
                new Point(6, 9),
                new Point(6, 3),
                new Point(9, 3),
                new Point(7, 3),
        };

        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points);
        assertEquals(0, fastCollinearPoints.numberOfSegments());
        assertEquals(0, fastCollinearPoints.segments().length);
    }

}
