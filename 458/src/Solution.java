public class Solution {
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int rounds = minutesToTest / minutesToDie;
        return buckets % rounds == 0 ? buckets / rounds : buckets / rounds + 1;
    }
}