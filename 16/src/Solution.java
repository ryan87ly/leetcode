import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        HashMap<Integer, Integer> map = new HashMap<>();
        List<List<Integer>> result = new LinkedList<>();
        int numLength = nums.length;
        for (int i = 0; i < numLength; ++ i){
            map.put(nums[i], i);
        }
        for (int i = 0; i < numLength; ++ i) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < numLength; ++ j) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                    for (int k = j + 1; k < numLength; ++ k) {
                        if (k > j + 1 && nums[k] == nums[k - 1]) continue;
                        int required = target - nums[i] - nums[j] - nums[k];
                        if (required < nums[k]) break;
                        Integer index = map.get(required);
                        if (index != null && index > i && index > j && index > k) {
                            result.add(Arrays.asList(nums[i], nums[j], nums[k], required));
                        }
                    }
            }
        }
        return result;
    }
}