import java.util.Arrays;

public class Solution {
    public int findContentChildren(int[] g, int[] s) {
        if (g.length == 0 || s.length == 0) return 0;
        Arrays.sort(g);
        Arrays.sort(s);
        int result = 0, a = g.length - 1, b = s.length - 1;
        while(a >= 0 && b >= 0) {
            if (g[a] <= s[b]) {
                ++ result;
                -- a;
                -- b;
            } else {
                -- a;
            }
        }
        return result;
    }
}