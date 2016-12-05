public class Solution {
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }
        int num = n ;
        while(num > 1) {
            int right = num >> 1;
            if (right << 1 != num && num != 1) {
                return false;
            }
            num = right;
        }
        return true;
    }

}