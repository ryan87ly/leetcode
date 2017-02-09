public class Solution {
    public int maxProfit(int k, int[] prices) {
        int length = prices.length;
        if (length <= 1) return 0;
        int[] dp = new int [length];
        if (k >= length / 2) {
            return withAnyTimes(prices);
        }
        int round = Math.min(k, length / 2);
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

    private int withAnyTimes(int[] prices) {
        int result = 0;
        for (int i = 1; i < prices.length; ++ i) {
            if (prices[i] > prices[i - 1]) result += prices[i] - prices[i - 1];
        }
        return result;
    }
}

