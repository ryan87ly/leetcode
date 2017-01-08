public class Solution {
    private final static int DIGIT = 31;
    public int hammingDistance(int x, int y) {
        int r = x ^ y;
        int result = 0;
        for (int i = 0; i < DIGIT; ++i) {
            result += (r >> i & 1);
        }
        return result;
    }
}