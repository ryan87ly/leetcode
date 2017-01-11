import java.util.function.BiFunction;
import java.util.function.Function;

public class Solution {
    private Integer[][][] dp;
    private int[] prices;
    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        if (isOneDirection(prices, (first, second) -> first >= second)) return 0;
        this.prices = prices;
        int length = prices.length;
        dp = new Integer[length + 1][length + 1][3];
        return maximum(1, 0, 2, length);
    }

    private boolean isOneDirection(int[] prices, BiFunction<Integer, Integer, Boolean> comp) {
        for (int i = 0, length = prices.length; i < length - 1; ++ i) {
            if (!comp.apply(prices[i], prices[i + 1])) {
                return false;
            }
        }
        return true;
    }

    private int maximum(int index, int selectedIndex, int remainingBuy, int maxIndex) {
        if (index > maxIndex) return 0;
        Integer r = dp[index][selectedIndex][remainingBuy];
        if (r != null) return r;
        if (selectedIndex == 0 && remainingBuy == 0) {
            r = 0;
        } else if (selectedIndex > 0) {
            int currentProfit = prices[index - 1] - prices[selectedIndex - 1];
            if (index == maxIndex) {
                r = Math.max(0, currentProfit);
            } else {
                if (prices[index] >= prices[index - 1] || currentProfit <= 0) {
                    r = maximum(index + 1, selectedIndex, remainingBuy, maxIndex);
                } else {
                    r = Math.max(maximum(index + 1, selectedIndex, remainingBuy, maxIndex), maximum(index + 1, 0, remainingBuy, maxIndex) + currentProfit);
                }
            }
        } else {
            r = 0;
            int i = index;
            while(i < maxIndex) {
                if (prices[i] <= prices[i - 1]) {
                    ++ i;
                } else {
                    r = Math.max(maximum(i + 1, 0, remainingBuy, maxIndex), maximum(i + 1, i, remainingBuy - 1, maxIndex));
                    break;
                }
            }
        }
        dp[index][selectedIndex][remainingBuy] = r;
        return r;
    }
}