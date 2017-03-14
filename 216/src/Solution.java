import java.util.LinkedList;
import java.util.List;

public class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new LinkedList<>();
        if (k > 9) return result;
        calculate(result, new LinkedList<Integer>(), 1, 0, k, n);
        return result;
    }

    private void calculate(List<List<Integer>> result, List<Integer> currentResult, int currentNumber, int currentSum, int count, int target) {
        int currentSize = currentResult.size();
        int sum = currentSum + currentNumber;
        if (sum > target) {
            return;
        } else if (currentSize == count - 1 && sum == target) {
            List<Integer> temp = new LinkedList<>(currentResult);
            temp.add(currentNumber);
            result.add(temp);
            return;
        }
        if (currentNumber >= 9) {
            return;
        }
        if (currentSize == count - 1 && sum < target){
            calculate(result, currentResult, currentNumber + 1, currentSum, count, target);
        } else if (currentSize < count - 1 && sum < target) {
            List<Integer> temp = new LinkedList<>(currentResult);
            temp.add(currentNumber);
            calculate(result, temp, currentNumber + 1, sum, count, target);
            calculate(result, currentResult, currentNumber + 1, currentSum, count, target);
        }
    }
}