import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestFastCollinearPoints {
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorThrows() {
        new FastCollinearPoints(null);
    }

    @Test
    public void testFindLineSegmentsCase1() {
        Point point1 = new Point(1, 1);
        Point point2 = new Point(2, 2);
        Point point3 = new Point(3, 3);
        Point point4 = new Point(4, 4);

        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(new Point[]{point1, point2, point3});
        assertEquals(0, fastCollinearPoints.numberOfSegments());

        fastCollinearPoints = new FastCollinearPoints(new Point[]{point1, point2, point3, point4});
        assertEquals(1, fastCollinearPoints.numberOfSegments());
        assertEquals("(1, 1) -> (4, 4)", fastCollinearPoints.segments()[0].toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindLineSegmentsCase2() {
        Point point1 = new Point(1, 1);
        Point point2 = new Point(1, 1);
        Point point3 = new Point(1, 1);
        Point point4 = new Point(1, 1);

        new FastCollinearPoints(new Point[]{point1, point2, point3, point4});
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindLineSegmentsCase3() {
        Point point1 = new Point(1, 2);
        Point point2 = new Point(3, 4);
        Point point3 = new Point(5, 6);
        Point point4 = new Point(5, 6);

        new FastCollinearPoints(new Point[]{point1, point2, point3, point4});
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindLineSegmentsCase4() {
        Point point1 = new Point(1, 2);
        Point point2 = new Point(3, 4);
        Point point3 = new Point(3, 4);
        Point point4 = new Point(5, 6);

        new FastCollinearPoints(new Point[]{point1, point2, point3, point4});
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindLineSegmentsCase5() {
        Point point1 = new Point(1, 2);
        Point point2 = new Point(1, 2);
        Point point3 = new Point(3, 4);
        Point point4 = new Point(5, 6);

        new FastCollinearPoints(new Point[]{point1, point2, point3, point4});
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindLineSegmentsCase6() {
        Point point1 = new Point(1, 2);
        Point point2 = new Point(1, 2);
        Point point3 = new Point(5, 6);
        Point point4 = new Point(5, 6);

        new FastCollinearPoints(new Point[]{point1, point2, point3, point4});
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindLineSegmentsCase7() {
        Point point1 = new Point(1, 1);
        Point point2 = new Point(1, 1);
        Point point3 = new Point(5, 5);
        Point point4 = new Point(5, 5);
        Point point5 = new Point(3, 3);
        Point point6 = new Point(7, 7);

        new FastCollinearPoints(new Point[]{point1, point2, point3, point4, point5, point6});
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
        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points);
        assertEquals(2, fastCollinearPoints.numberOfSegments());
        assertEquals("(1, 1) -> (4, 4)", fastCollinearPoints.segments()[0].toString());
        assertEquals("(1, 3) -> (4, 12)", fastCollinearPoints.segments()[1].toString());
    }

    @Test
    public void testFindLineSegmentsCase9() {
        Point pointA1 = new Point(1, 1); // p
        Point pointA2 = new Point(2, 2); // q
        Point pointA3 = new Point(3, 3); // r
        Point pointA4 = new Point(4, 4); // s
        Point pointA5 = new Point(5, 5); // t
        Point pointA6 = new Point(6, 6); // x

        Point pointB1 = new Point(1, 3); // p
        Point pointB2 = new Point(2, 6); // q
        Point pointB3 = new Point(3, 9); // r
        Point pointB4 = new Point(4, 12); // s
        Point pointB5 = new Point(5, 15); // t
        Point pointB6 = new Point(6, 18); // x

        Point[] points = new Point[]{pointA1, pointA2, pointA3, pointA4, pointA5, pointA6, pointB1, pointB2, pointB3, pointB4, pointB5, pointB6};
        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points);
        assertEquals(2, fastCollinearPoints.numberOfSegments());
        assertEquals("(1, 1) -> (6, 6)", fastCollinearPoints.segments()[0].toString());
        assertEquals("(1, 3) -> (6, 18)", fastCollinearPoints.segments()[1].toString());
    }

    @Test
    public void testFindLineSegmentsCase10() {
        Point point1 = new Point(10000, 0);
        Point point2 = new Point(0, 10000);
        Point point3 = new Point(3000, 7000);
        Point point4 = new Point(7000, 3000);
        Point point5 = new Point(20000, 21000);
        Point point6 = new Point(3000, 4000);
        Point point7 = new Point(14000, 15000);
        Point point8 = new Point(6000, 7000);

        Point[] points = new Point[] {point1, point2, point3, point4, point5, point6, point7, point8};
        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points);
        assertEquals(2, fastCollinearPoints.numberOfSegments());
        assertEquals("(10000, 0) -> (0, 10000)", fastCollinearPoints.segments()[0].toString());
        assertEquals("(3000, 4000) -> (20000, 21000)", fastCollinearPoints.segments()[1].toString());
    }

    @Test
    public void testFindLineSegmentsCase11() {
        Point[] points = new Point[] {
            new Point(19809, 11135),
            new Point(10540, 10965),
            new Point(8091, 10965),
            new Point(20714, 20774),
            new Point(3075,  4346),
            new Point(14198, 11135),
            new Point(20530, 10965),
            new Point(17298, 11135),
            new Point(8368, 20774),
            new Point(19860, 14809),
            new Point(18237, 20774),
            new Point(17276, 14809),
            new Point(3744, 14809),
            new Point(15339,  4346),
            new Point(10583,  4346),
            new Point(14927, 14809),
            new Point(3808, 10965),
            new Point(16053,  4346),
            new Point(1368, 11135),
            new Point(9347, 20774)
        };

        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points);
        assertEquals(5, fastCollinearPoints.numberOfSegments());
        assertEquals("(1368, 11135) -> (19809, 11135)", fastCollinearPoints.segments()[0].toString());
        assertEquals("(3808, 10965) -> (20530, 10965)", fastCollinearPoints.segments()[1].toString());
        assertEquals("(8368, 20774) -> (20714, 20774)", fastCollinearPoints.segments()[2].toString());
        assertEquals("(3075, 4346) -> (16053, 4346)", fastCollinearPoints.segments()[3].toString());
        assertEquals("(3744, 14809) -> (19860, 14809)", fastCollinearPoints.segments()[4].toString());
    }
}
