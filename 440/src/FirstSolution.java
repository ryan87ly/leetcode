public class FirstSolution {
    private static final int[] NUM_MAP = {
        0, 1, 11, 111, 1111, 11111, 111111, 1111111, 11111111, 111111111
    };

    private static final int[] MAX_NUM_MAP = {
       1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000
    };

    public int findKthNumber(int n, int k) {
        int remainIndex = k;
        int digitNum = countDigit(n);
        int[] slots = new int[10];
        int remainingNum = n - NUM_MAP[digitNum - 1] * 9;
        for (int i = 1; i < 10; ++ i) {
            slots[i] = NUM_MAP[digitNum - 1] + (remainingNum >= MAX_NUM_MAP[digitNum - 1] ? MAX_NUM_MAP[digitNum - 1] : remainingNum);
            remainingNum = Math.max(0, remainingNum - MAX_NUM_MAP[digitNum - 1]);
        }
        int[] rr = new int[1];
        for (int i = 1; i < 10; ++ i) {
            if (slots[i] >= remainIndex) {
                rr[0] = i;
                find(rr, digitNum - 1, slots[i] - NUM_MAP[digitNum - 1], remainIndex);
                break;
            } else {
                remainIndex = remainIndex - slots[i];
            }
        }
        return rr[0];
    }

    private void find(int[] rr, int digitNum, int n, int k) {
        if (k == 1) return;
        int[] slots = new int[10];
        int remainingNum = n;
        int remainIndex = k - 1;
        for (int i = 0; i < 10; ++ i) {
            slots[i] = NUM_MAP[digitNum - 1] + (remainingNum >= MAX_NUM_MAP[digitNum - 1] ? MAX_NUM_MAP[digitNum - 1] : remainingNum);
            remainingNum = Math.max(0, remainingNum - MAX_NUM_MAP[digitNum - 1]);
        }
        for (int i = 0; i < 10; ++ i) {
            if (slots[i] >= remainIndex) {
                rr[0] = rr[0] * 10 + i;
                find(rr, digitNum - 1, slots[i] - NUM_MAP[digitNum - 1], remainIndex);
                break;
            } else {
                remainIndex = remainIndex - slots[i];
            }
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