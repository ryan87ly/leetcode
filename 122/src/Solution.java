public class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length <= 1) return 0;
        int prev = prices[0];
        int result = 0;
        for (int i = 1; i < prices.length; ++ i) {
            int current = prices[i];
            if (current > prev) result += (current - prev);
            prev = current;
        }
        return result;
    }
}