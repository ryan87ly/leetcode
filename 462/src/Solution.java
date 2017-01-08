import java.util.Arrays;

public class Solution {
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int result = 0;
        for (int i = 0, length = nums.length; i < length/2; ++ i) {
            result += nums[length - 1 - i] - nums[i];
        }
        return result;
    }
}