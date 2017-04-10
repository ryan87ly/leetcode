import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new LinkedList<>();
        calculate(result, new LinkedList<>(), candidates, candidates.length - 1, target);
        return result;
    }

    private void calculate(List<List<Integer>> result, List<Integer> currentList, int[] candidates, int currentIndex, int target) {
        if (currentIndex < 0) return;
        int num = candidates[currentIndex];
        int count = target / num;
        for (int j = 0; j <= count; ++ j) {
            int remaining = target - j * num;
            if (remaining < 0) {
                break;
            }
            List<Integer> temp = new LinkedList<>(currentList);
            for (int k = 1; k <= j; ++ k){
                temp.add(num);
            }
            if (remaining == 0) {
                result.add(temp);
            } else {
                calculate(result, temp, candidates, currentIndex - 1, remaining);
            }
        }
    }
}