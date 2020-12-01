import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private LineSegment[] segmentArray = new LineSegment[1];
    private int segmentCount = 0;

    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }

        Point origin = points[0];
        this.execute(points, origin);
    }

    private void execute(Point[] points, Point origin) {
        double[] slopes = new double[points.length - 1];

        for (int i = 1; i < points.length; i++) {
            slopes[i - 1] = origin.slopeTo(points[i]);
        }

        Arrays.sort(slopes);

        ArrayList<Point> collinearPoints = new ArrayList<>();
        ArrayList<String> existingCollinearPointStrings = new ArrayList<>();
        collinearPoints.add(origin);

        int slopesLength = slopes.length;
        for (int j = 0; j < slopesLength; j++) {
            Point currentPoint = points[j + 1];

            if (existingCollinearPointStrings.contains(currentPoint.toString())) {
                continue;
            }

            if ((j != slopesLength - 1 && slopes[j] == slopes[j + 1]) || (j != 0 && slopes[j] == slopes[j - 1])) {
                existingCollinearPointStrings.add(currentPoint.toString());
                collinearPoints.add(currentPoint);
            } else {
                collinearPoints = this.addCollinearPoints(collinearPoints, origin);
            }
        }

        this.addCollinearPoints(collinearPoints, origin);
    }

    private ArrayList<Point> addCollinearPoints(ArrayList<Point> collinearPoints, Point origin) {
        int collinearPointSize = collinearPoints.size();

        if (collinearPointSize > 3) {
            if (this.segmentArray.length <= (this.segmentCount + collinearPointSize)) {
                LineSegment[] newSegmentArray = new LineSegment[(this.segmentCount + collinearPointSize) * 2];
                for (int i = 0; i < this.segmentArray.length; i++) {
                    newSegmentArray[i] = this.segmentArray[i];
                }
                this.segmentArray = newSegmentArray;
            }

            for (int k = 0; k < collinearPointSize - 1; k++) {
                this.segmentArray[this.segmentCount] = new LineSegment(collinearPoints.get(k), collinearPoints.get(k + 1));
                this.segmentCount++;
            }
        }

        collinearPoints = new ArrayList<>();
        collinearPoints.add(origin);

        return collinearPoints;
    }

    public int numberOfSegments() {
        return this.segmentCount;
    }

    public LineSegment[] segments() {
        return this.segmentArray;
    }
}

