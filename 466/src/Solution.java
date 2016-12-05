public class Solution {
    private Result[] results;

    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        int s2Length = s2.length();
        results = new Result[s2Length];
        int currentCount = 0, currentIndex = 0;
        for (int i = 0; i < n1; ++i) {
            Result r = f(s1, s2, currentIndex);
            currentCount += r.count;
            currentIndex = r.index;
        }
        return currentCount / n2;
    }

    private Result f(String s1, String s2, int index) {
        Result result = results[index];
        if (result != null) return result;
        int count = 0, currentIndex = index, s2StringLength = s2.length();
        for (int i = 0, s1Length = s1.length(); i < s1Length; ++i ){
            char firstStringChar = s1.charAt(i);
            char secondStringChar = s2.charAt(currentIndex);
            if (firstStringChar == secondStringChar) {
                if (++ currentIndex == s2StringLength) {
                    ++ count;
                    currentIndex = 0;
                }
            }
        }
        Result r = new Result(count, currentIndex);
        results[index] = r;
        return r;
    }

    private static class Result {
        private final int count;
        private final int index;

        private Result(int count, int index) {
            this.count = count;
            this.index = index;
        }
    }
}