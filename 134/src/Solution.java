public class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0, totalCost = 0, maxGap = 0, maxGapIndex = 0;
        int[] gaps = new int[gas.length];
        for(int i = 0; i < gas.length; ++ i) {
            totalGas += gas[i];
            totalCost += cost[i];
            int gap = gas[i] - cost[i];
            gaps[i] = gap;
            if (gap > maxGap) {
                maxGap = gap;
                maxGapIndex = i;
            }
        }
        if (totalGas < totalCost) return -1;
        for(int i = 0; i < gas.length; ++ i) {
            int currentGap = 0;
            boolean valid = true;
            for (int j = 0; j < gas.length; ++ j) {
                int index = (maxGapIndex + i + j) % gas.length;
                currentGap += gaps[index];
                if (currentGap < 0) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                return (maxGapIndex + i) % gas.length;
            }
        }
        return -1;
    }
}