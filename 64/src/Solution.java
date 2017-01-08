public class Solution {
    private int[][] result;
    private int[][] grid;
    public int minPathSum(int[][] grid) {
        this.grid = grid;
        int width = grid.length;
        int height = grid[0].length;
        result = new int[width][height];
        for (int i = 0; i < width; ++ i) {
            for (int j = 0; j < height; ++ j) {
                result[i][j] = -1;
            }
        }
        result[0][0] = grid[0][0];
        for (int i = 1; i < width; ++ i) {
            result[i][0] = result[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < height; ++ i) {
            result[0][i] = result[0][i - 1] + grid[0][i];
        }
        return dp(width - 1, height - 1);
    }

    private int dp(int x, int y) {
        if (result[x][y] != -1) return result[x][y];
        int r = Math.min(dp(x, y - 1),dp(x - 1, y)) + grid[x][y];
        result[x][y] = r;
        return r;
    }
}