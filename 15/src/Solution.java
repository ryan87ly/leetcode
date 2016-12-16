import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
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
                int required = -nums[i] - nums[j];
                Integer index = map.get(required);
                if (index != null && index > i && index > j) {
                    result.add(Arrays.asList(nums[i], nums[j], required));
                }
            }
        }
        return result;
    }
}