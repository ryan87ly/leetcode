public class Solution {
    private int[][] result;
    public int getMoneyAmount(int n) {
        result = new int[n + 1][n + 1];
        for (int i = 1; i < n + 1; ++i) {
            for (int j = 1; j < n + 1; ++j) {
                result[i][j] = -1;
            }
        }
        return f(1, n);
    }

    private int f(int min, int max) {
        if (result[min][max] != -1) return result[min][max];
        int diff = max - min;
        if (diff == 0) return 0;
        if (diff == 1) return min;
        if (diff == 2) return min + 1;
        if (diff == 3) return min + max - 1; // 1st, 3th
        int currentMin = Integer.MAX_VALUE;
        for (int i = 1; i < diff; ++i){
            int selected = min + i;
            currentMin = Math.min(Math.max(f(min, selected - 1), f(selected + 1, max)) + selected, currentMin);
        }
        result[min][max] = currentMin;
        return currentMin;
    }

}
