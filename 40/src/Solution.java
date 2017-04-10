import java.util.*;

public class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        Set<List<Integer>> sets = new HashSet<>();
        calculate(sets, new LinkedList<>(), candidates, candidates.length - 1, target);
        return new ArrayList<>(sets);
    }

    private void calculate(Set<List<Integer>> result, List<Integer> currentList, int[] candidates, int currentIndex, int target) {
        if (currentIndex < 0) return;
        int num = candidates[currentIndex];
        for(int i = 1; i >=0; -- i){
            int remaining = target - i * num;
            if (remaining < 0) {
                continue;
            }
            List<Integer> temp = new LinkedList<>(currentList);
            if (i == 1) temp.add(num);
            if (remaining == 0) {
                result.add(temp);
            } else {
                calculate(result, temp, candidates, currentIndex - 1, remaining);
            }
        }
    }
}