import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        int numLength = nums.length;
        if (numLength == 0) return result;
        result.add(Arrays.asList(nums[0]));
        for (int i = 1; i < numLength; ++ i) {
            List<List<Integer>> currentResult = new LinkedList<>();
            int currentNum = nums[i];
            for (List<Integer> r : result) {
                for (int j = 0; j <= i; j ++) {
                    List<Integer> n = new ArrayList<>(r);
                    n.add(j, currentNum);
                    currentResult.add(n);
                }
            }
            result = currentResult;
        }
        return result;
    }
}
