public class Solution {
    public String getPermutation(int n, int k) {
        boolean[] nums = new boolean[n + 1];
        int remaining = k;
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; ++i) {
            int mod = permutation(n - i);
            int r = remaining % mod;
            int index = remaining / mod;
            if (r == 0) -- index;
            int number = getElement(nums, index);
            sb.append(number);
            remaining = remaining - index * mod;
        }
        return sb.toString();
    }

    private int getElement(boolean[] nums, int n) {
        for (int i = 0, count = 0, numLength = nums.length; i < numLength; ++ i) {
            if (!nums[i]) {
                if (count >= n) {
                    nums[i] = true;
                    return i + 1;
                }
                ++ count;
            }
        }
        return -1;
    }

    private int permutation(int n) {
        int result = 1;
        for (int i = 1; i <=n ; ++i) {
            result *= i;
        }
        return result;
    }
}