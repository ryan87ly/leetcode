import java.util.Arrays;

public class Solution {
    public String[] findRelativeRanks(int[] nums) {
        Integer[] indice = new Integer[nums.length];
        for (int i = 0; i < nums.length; ++ i) indice[i] = i;
        Arrays.sort(indice, (i, j) -> nums[j] - nums[i]);

        String[] results = new String[nums.length];
        for (int i = 0; i < nums.length; ++ i) {
            if (i == 0) results[indice[i]] = "Gold Medal";
            else if (i == 1) results[indice[i]] = "Silver Medal";
            else if (i == 2) results[indice[i]] = "Bronze Medal";
            else results[indice[i]] = String.valueOf(i + 1);
        }
        return results;
    }
}