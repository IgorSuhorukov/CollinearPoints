import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private ArrayList<LineSegment> segmentArray = new ArrayList<>();

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

                        Point[] currentPoints = new Point[]{pPoint, qPoint, rPoint, sPoint};
                        Arrays.sort(currentPoints);
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
                            this.segmentArray.add(lineSegment);
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
        return this.segmentArray.size();
    }

    public LineSegment[] segments() {
        return this.segmentArray.toArray(new LineSegment[this.segmentArray.size()]);
    }
}
