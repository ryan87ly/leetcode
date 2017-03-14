import java.util.Arrays;

public class Solution {
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) return 0;
        Arrays.sort(points, (a, b) -> a[1] - b[1]);
        int count = 1, currentRight = points[0][1];
        for (int i = 1; i < points.length; ++ i) {
            if (points[i][0] > currentRight) {
                ++ count;
                currentRight = points[i][1];
            }
        }
        return count;
    }
}