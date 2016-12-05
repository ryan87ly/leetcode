import java.util.HashMap;

public class Solution {

    public int maxPoints(Point[] points) {
        int length = points.length;
        if (length == 0) return 0;
        if (length == 1) return 1;
        int max = 0;
        for (int i = 0; i < length; ++i) {
            int equalsPoints = 0;
            int currentMax = 1;
            Point currentPoint = points[i];
            HashMap<Float, Integer> map = new HashMap<>();
            for (int j = i + 1; j < length; ++j ){
                Point point = points[j];
                if (currentPoint.x == point.x && currentPoint.y == point.y) ++ equalsPoints;
                else {
                    float slope;
                    if (currentPoint.x == point.x) slope = Float.MAX_VALUE;
                    else slope = (float) (point.y - currentPoint.y) / (float) (point.x - currentPoint.x);
                    if (slope == -0.0f) slope = 0.0f;
                    Integer count = map.get(slope);
                    if (count == null) map.put(slope, 2);
                    else map.put(slope, ++count);
                }
            }
            for (int c : map.values()) {
                currentMax = Math.max(currentMax, c);
            }
            max = Math.max(max, currentMax + equalsPoints);
        }
        return max;
    }
}