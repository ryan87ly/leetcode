public class SecondSolution {
    private static final int[] NUM_MAP = {
        0, 1, 11, 111, 1111, 11111, 111111, 1111111, 11111111, 111111111
    };

    private static final int[] MAX_NUM_MAP = {
       1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000
    };

    public int findKthNumber(int n, int k) {
        int digitNum = countDigit(n);
        int remainingNum = n - NUM_MAP[digitNum - 1] * 9;
        int result = 0;
        int remainIndex = k;
        boolean isFirstRound = true;
        while(true) {
            if (!isFirstRound && remainIndex == 1) return result;
            if (!isFirstRound) -- remainIndex;
            int[] slots = new int[10];
            for (int i = isFirstRound ? 1 : 0; i < 10; ++ i){
                slots[i] = NUM_MAP[digitNum - 1] + (remainingNum >= MAX_NUM_MAP[digitNum - 1] ? MAX_NUM_MAP[digitNum - 1] : remainingNum);
                remainingNum = Math.max(0, remainingNum - MAX_NUM_MAP[digitNum - 1]);
            }
            for (int i = isFirstRound ? 1 : 0; i < 10; ++ i) {
                if (slots[i] >= remainIndex) {
                    result = result * 10 + i;
                    remainingNum = slots[i] - NUM_MAP[digitNum - 1];
                    -- digitNum;
                    break;
                } else {
                    remainIndex = remainIndex - slots[i];
                }
            }
            isFirstRound = false;
        }
    }

    private int countDigit(int num) {
        if (num == 0) return 1;
        int result = 0, tmp = num;
        while(tmp > 0) {
            ++ result;
            tmp = tmp / 10;
        }
        return result;
    }
}