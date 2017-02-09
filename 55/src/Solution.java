public class Solution {
    public boolean canJump(int[] nums) {
        if (nums.length == 1) return true;
        int maxReach = 0;
        for (int i = 0; i <= maxReach; ++ i) {
            maxReach = Math.max(i + nums[i], maxReach);
            if (maxReach >= nums.length - 1) return true;
        }
        return false;
    }
}