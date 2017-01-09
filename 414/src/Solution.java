import java.util.HashSet;

public class Solution {
    private static final int TARGET_INDEX = 2;

    public int thirdMax(int[] nums) {
        HashSet<Integer> s = new HashSet();
        for (int num : nums) {
            s.add(num);
        }
        Integer[] numbers = s.toArray(new Integer[0]);
        if (numbers.length == 1) return numbers[0];
        if (numbers.length == 2) return Math.max(numbers[0], numbers[1]);
        return findNum(numbers, 0, numbers.length - 1);
    }

    private int findNum(Integer[] nums, int beginIndex, int endIndex) {
        if (beginIndex == endIndex) {
            return nums[beginIndex];
        }
        int pivot = nums[beginIndex];
        int i = beginIndex + 1, j = endIndex;
        while(i < j) {
            while(nums[j] < pivot && i < j) j--;
            while(nums[i] > pivot && i < j) i++;
            swap(nums, i ,j);
        }
        if (pivot < nums[i]) {
            if (i == TARGET_INDEX) return pivot;
            swap(nums, beginIndex, i);
            if (i > TARGET_INDEX) return findNum(nums, beginIndex, i - 1);
            else return findNum(nums, i + 1, endIndex);
        } else {
            if (beginIndex == TARGET_INDEX) return nums[beginIndex];
            else return findNum(nums, beginIndex + 1, endIndex);
        }
    }

    private void swap(Integer[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}