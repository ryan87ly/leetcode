public class Solution {
    public int uniquePaths(int m, int n) {
        Integer[][] dp = new Integer[m][n];
        for (int i = 0; i < m; ++ i) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; ++j) {
            dp[0][j] = 1;
        }
        return paths(dp, m - 1, n - 1);
    }

    private int paths(Integer[][] dp, int i, int j) {
        Integer v = dp[i][j];
        if (v != null) return v;
        int r = paths(dp, i - 1, j) + paths(dp, i, j - 1);
        dp[i][j] = r;
        return r;
    }
}