public class Solution {
     public int findKthNumber(int n, int k) {
         int current = 1;
         --k;
         while(k > 0) {
             int step = calculateSteps(n, current, current + 1);
             if (k >= step) {
                 ++ current;
                 k = k - step;
             } else {
                 current *= 10;
                 k = k -1;
             }
         }
         return current;
     }

     private int calculateSteps(int n, long n1, long n2) {
         int steps = 0;
         while(n1 <= n) {
             if (n < n2) steps += (n - n1 + 1);
             else steps += (n2 - n1);
             n1 = n1 * 10;
             n2 = n2 * 10;
         }
         return steps;
     }
}
