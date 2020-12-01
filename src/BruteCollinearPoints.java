import java.util.Arrays;

public class BruteCollinearPoints {
    private LineSegment[] segmentArray = new LineSegment[4];
    private int segmentCount = 0;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }

        for (int p = 0; p < points.length; p++) {
            Point pPoint = points[p];
            for (int q = p + 1; q < points.length; q++) {
                Point qPoint = points[q];
                for (int r = q + 1; r < points.length; r++) {
                    Point rPoint = points[r];
                    for (int s = r + 1; s < points.length; s++) {
                        Point sPoint = points[s];

                        if (
                            !this.areDifferentCollinearPoints(pPoint, qPoint, rPoint) ||
                            !this.areDifferentCollinearPoints(pPoint, qPoint, sPoint) ||
                            !this.areDifferentCollinearPoints(pPoint, rPoint, sPoint)
                        ) {
                            continue;
                        }

                        if (this.segmentArray.length <= this.segmentCount) {
                            LineSegment[] newSegmentArray = new LineSegment[this.segmentCount * 2];
                            for (int i = 0; i < this.segmentArray.length; i++) {
                                newSegmentArray[i] = this.segmentArray[i];
                            }
                            this.segmentArray = newSegmentArray;
                        }

                        Point[] currentPoints = new Point[]{pPoint, qPoint, rPoint, sPoint};
                        LineSegment lineSegment = new LineSegment(currentPoints[0], currentPoints[3]);

                        boolean exists = false;
                        for (LineSegment segment : this.segmentArray) {
                            if (segment == null) {
                                continue;
                            }
                            if (segment.toString().equals(lineSegment.toString())) {
                                exists = true;
                            }
                        }

                        if (!exists) {
                            this.segmentArray[this.segmentCount] = lineSegment;
                            this.segmentCount++;
                        }
                    }
                }
            }
        }
    }

    private boolean areDifferentCollinearPoints(Point mainPoint, Point aPoint, Point bPoint) {
        boolean arePointsIdentical = mainPoint.compareTo(aPoint) == 0 || mainPoint.compareTo(bPoint) == 0 || aPoint.compareTo(bPoint) == 0;
        return !arePointsIdentical && mainPoint.slopeTo(aPoint) == mainPoint.slopeTo(bPoint);
    }

    public int numberOfSegments() {
        return this.segmentCount;
    }

    public LineSegment[] segments() {
        return this.segmentArray;
    }
}
