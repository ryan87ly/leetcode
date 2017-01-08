public class Solution {
    private static int DIGIT = 31;
    private static int[] BIT_POSITION;
    static {
        BIT_POSITION = new int[DIGIT];
        BIT_POSITION[0] = 1;
        for (int i = 1; i < DIGIT; ++ i) {
            BIT_POSITION[i] = BIT_POSITION[i - 1] << 1;
        }
    }

    public int totalHammingDistance(int[] nums) {
        int length = nums.length;
        int result = 0;
        for (int j = 0; j < DIGIT; ++j) {
            int count = 0;
            for (int i = 0; i < length; ++ i) {
                int num = nums[i];
                if ((num & BIT_POSITION[j]) > 0) count += 1;
            }
            result += count * (length - count);
        }
        return result;
    }
}