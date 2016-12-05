public class Solution2 {
    public int getMoneyAmount(int n) {
        if (n <= 1) return 0;
        return f(1, n);
    }

    private int f(int min, int max) {
        int diff = max - min;
        if (diff == 0) return 0;
        if (diff == 1) return min;
        if (diff == 2) return (min + max) / 2 ;
        if (diff == 3) return min + max - 1; // 1st, 3th
        int mid = (min + max) / 2;
        return mid + f(mid + 1, max);
    }
}