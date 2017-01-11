public class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        int minNum = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; ++ i) {
            maxProfit = Math.max(maxProfit, prices[i] - minNum);
            minNum = Math.min(minNum, prices[i]);
        }
        return maxProfit;
    }
}