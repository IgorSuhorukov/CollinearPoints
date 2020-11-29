import java.util.Arrays;

public class BruteCollinearPoints {
    private LineSegment[] segmentArray = new LineSegment[1];
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
                        if (pPoint.slopeTo(qPoint) != pPoint.slopeTo(rPoint) || pPoint.slopeTo(qPoint) != pPoint.slopeTo(sPoint)) {
                            continue;
                        }

                        if (this.segmentArray.length <= this.segmentCount) {
                            LineSegment[] newSegmentArray = new LineSegment[this.segmentCount * 2];
                            for (int i = 0; i < this.segmentArray.length; i++) {
                                newSegmentArray[i] = this.segmentArray[i];
                            }
                            this.segmentArray = newSegmentArray;
                        }

                        Point[] currentPoints = new Point[] {pPoint, qPoint, rPoint, sPoint};
                        Arrays.sort(currentPoints);

                        this.segmentArray[this.segmentCount] = new LineSegment(currentPoints[0], currentPoints[3]);
                        this.segmentCount++;
                    }
                }
            }
        }
    }

    public int numberOfSegments() {
        return this.segmentCount;
    }

    public LineSegment[] segments() {
        return this.segmentArray;
    }
}
