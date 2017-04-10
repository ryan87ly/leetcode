import java.util.Arrays;

public class Solution {
    public int eraseOverlapIntervals(Interval[] intervals) {
        Arrays.sort(intervals, (a, b) -> a.end - b.end);
        int count = 0, end = Integer.MIN_VALUE;
        for(int i = 0; i < intervals.length; ++ i) {
            if (intervals[i].start >= end) {
                count ++;
                end = intervals[i].end;
            }
        }
        return intervals.length - count;
    }
}