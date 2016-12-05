import java.util.HashMap;

public class Solution {
    private HashMap<Integer, Boolean> map = new HashMap<>();
    private int maxNum;

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (maxChoosableInteger <= 0) return false;
        if (desiredTotal <= 0) return true;
        if ( (1 + maxChoosableInteger) * maxChoosableInteger / 2 < desiredTotal) return false;
        maxNum = maxChoosableInteger;
        return canWin(0, desiredTotal);
    }

    private boolean canWin(int state, int remaining) {
        if (remaining <= 0) return false;
        if (!map.containsKey(state)) {
            for (int i = 1; i <= maxNum; ++i) {
                int current = 1 << i;
                if ((state & current) == 0) {
                    if (!canWin((state | current), remaining - i)) {
                        map.put(state, true);
                        return true;
                    }
                }
            }
        } else {
            return map.get(state);
        }
        map.put(state, false);
        return false;
    }
}