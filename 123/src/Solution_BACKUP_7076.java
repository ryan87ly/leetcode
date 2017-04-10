public class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length <= 1) return 0;
        return maximum(2, prices);
    }

    private int maximum(int round, int[] prices) {
        int length = prices.length;
        int[] dp = new int [length];
        for (int i = 1; i <= round; ++ i) {
            int currentMax = dp[0] - prices[0];
            for (int j = 1; j < length; ++j){
                int temp = dp[j];
                dp[j] = Math.max(dp[j-1], prices[j] + currentMax);
                currentMax = Math.max(currentMax, temp - prices[j]);
            }
        }
        return dp[length - 1];
    }
}