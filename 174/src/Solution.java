public class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int width = dungeon.length;
        int height = dungeon[0].length;
        Integer[][] dp = new Integer[width][height];
        return minCost(dungeon, dp, 0, 0, width, height);
    }

    private int minCost(int[][] dungeon, Integer[][] dp, int i, int j, int width, int height) {
        Integer v = dp[i][j];
        if (v != null) return v;
        int nextMinHp;
        if (i == width - 1 && j == height - 1) {
            nextMinHp = 1;
        } else if (i == width - 1){
            nextMinHp = minCost(dungeon, dp, i, j + 1, width, height);
        } else if (j == height - 1) {
            nextMinHp = minCost(dungeon, dp, i + 1, j, width, height);
        } else {
            nextMinHp = Math.min(minCost(dungeon, dp, i + 1, j, width, height), minCost(dungeon, dp, i, j + 1, width, height));
        }
        dp[i][j] = minHp(dungeon[i][j], nextMinHp);
        return dp[i][j];
    }

    private int minHp(int currentValue, int nextMinHp) {
        int needed = nextMinHp - currentValue;
        if (needed > 0) {
            return needed;
        } else {
            return 1;
        }
    }
}