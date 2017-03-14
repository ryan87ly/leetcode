public class Solution {
    public int jump(int[] nums) {
        if (nums.length <= 1) return 0;
        int round = 1, lastRoundMaxIndex = 0, currentIndex = 0, maxIndex = 0;
        while(true) {
            int step = nums[currentIndex];
            maxIndex = Math.max(maxIndex, step + currentIndex);
            if (maxIndex >= nums.length - 1) return round;
            if (currentIndex == lastRoundMaxIndex) {
                ++ round;
                lastRoundMaxIndex = maxIndex;
            }
            ++ currentIndex;
        }
    }
}