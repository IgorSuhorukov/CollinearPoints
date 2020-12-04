import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private final ArrayList<LineSegment> segmentArray = new ArrayList<>();

    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }

        ArrayList<String> existingSegmentArrays = new ArrayList<>();

        for (Point originalPoint : points) {
            ArrayList<Point> pointList = new ArrayList<>();
            ArrayList<String> pointStringList = new ArrayList<>();

            Point[] newPoints = new Point[points.length];
            System.arraycopy(points, 0, newPoints, 0, points.length);

            Arrays.sort(newPoints, originalPoint.slopeOrder());
            pointList.add(originalPoint);

            for (int i = 0; i < newPoints.length; i++) {
                Point currentPoint = newPoints[i];

                if (currentPoint == originalPoint) {
                    continue;
                }

                Point nextPoint = null;

                if (i < newPoints.length - 1 && newPoints[i + 1] == originalPoint) {
                    if (i < newPoints.length - 3 && i + 2 <= newPoints.length - 1) {
                        nextPoint = newPoints[i + 2];
                    }
                } else {
                    if (i < newPoints.length - 1) {
                        nextPoint = newPoints[i + 1];
                    }
                }

                if (nextPoint != null && !pointStringList.contains(currentPoint.toString()) && originalPoint.slopeTo(currentPoint) == originalPoint.slopeTo(nextPoint)) {
                    pointStringList.add(currentPoint.toString());
                    pointList.add(currentPoint);
                } else {
                    Point previousPoint = null;
                    if (i != 0) {
                        if (newPoints[i - 1] == originalPoint) {
                            if (i - 2 >= 0) {
                                previousPoint = newPoints[i - 2];
                            }
                        } else {
                            previousPoint = newPoints[i - 1];
                        }
                    }

                    if (previousPoint != null && !pointStringList.contains(currentPoint.toString()) && originalPoint.slopeTo(currentPoint) == originalPoint.slopeTo(previousPoint)) {
                        pointStringList.add(currentPoint.toString());
                        pointList.add(currentPoint);
                    }
                }
            }

            int pointListSize = pointList.size();
            if (pointListSize >= 4) {
                Point[] pointArr = pointList.toArray(new Point[pointList.size()]);
                Arrays.sort(pointArr);

                LineSegment lineSegment = new LineSegment(pointArr[0], pointArr[pointArr.length - 1]);
                if (existingSegmentArrays.contains(lineSegment.toString())) {
                    continue;
                }
                existingSegmentArrays.add(lineSegment.toString());
                this.segmentArray.add(lineSegment);
            }
        }
    }

    public int numberOfSegments() {
        return this.segmentArray.size();
    }

    public LineSegment[] segments() {
        return this.segmentArray.toArray(new LineSegment[this.segmentArray.size()]);
    }
}

