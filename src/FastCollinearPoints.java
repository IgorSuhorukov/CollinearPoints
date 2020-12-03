import edu.princeton.cs.algs4.Alphabet;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private final ArrayList<String> existingSegmentArrays = new ArrayList<>();
    private ArrayList<LineSegment> segmentArray = new ArrayList<>();

    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }

        for (Point originalPoint : points) {
            ArrayList<Point> pointList = new ArrayList<>();
            ArrayList<String> pointStringList = new ArrayList<>();
            Arrays.sort(points, originalPoint.slopeOrder());
            pointList.add(originalPoint);

            for (int i = 0; i < points.length; i++) {
                Point currentPoint = points[i];

                if (currentPoint == originalPoint) {
                    continue;
                }

                Point nextPoint = null;;
                if (i < points.length - 1 && points[i + 1] == originalPoint) {
                    if (i < points.length - 3 && i + 2 <= points.length - 1) {
                        nextPoint = points[i + 2];
                    }
                } else {
                    if (i < points.length - 1) {
                        nextPoint = points[i + 1];
                    }
                }

                if (nextPoint != null && !pointStringList.contains(currentPoint.toString()) && originalPoint.slopeTo(currentPoint) == originalPoint.slopeTo(nextPoint)) {
                    pointStringList.add(currentPoint.toString());
                    pointList.add(currentPoint);
                } else {
                    Point previousPoint = null;
                    if (i != 0) {
                        if (points[i - 1] == originalPoint) {
                            if (i - 2 >= 0) {
                                previousPoint = points[i - 2];
                            }
                        } else {
                            previousPoint = points[i - 1];
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
                if (this.existingSegmentArrays.contains(lineSegment.toString())) {
                    continue;
                }
                this.existingSegmentArrays.add(lineSegment.toString());
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

