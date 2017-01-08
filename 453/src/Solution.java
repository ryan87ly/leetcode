public class Solution {
    public int minMoves(int[] nums) {
        int min = Integer.MAX_VALUE;
        int length = nums.length;
        for (int i = 0; i < length; ++ i) {
            min = Math.min(min, nums[i]);
        }
        int result = 0;
        for (int i = 0; i < length; ++ i) {
            result += (nums[i] - min);
        }
        return result;
    }
}