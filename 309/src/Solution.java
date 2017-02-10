public class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length <= 1) return 0;
        int b0 = -prices[0], s0 = 0, b1, s1 = 0, s2 = 0;
        for(int i = 1; i < prices.length; ++i) {
            b1 = Math.max(b0, s0 - prices[i]);
            s2 = Math.max(s1, b0 + prices[i]);
            b0 = b1;
            s0 = s1;
            s1 = s2;
        }
        return s2;
    }
}