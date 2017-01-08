public class Solution {

    public int thirdMax(int[] nums) {
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        return findNum(nums, 0, nums.length - 1);
    }

    private int findNum(int[] nums, int beginIndex, int endIndex) {
        int pivot = nums[beginIndex];
        int i = beginIndex + 1, j = endIndex;
        while(i < j) {
            while(nums[i] >= pivot && i < j) i++;
            while(nums[j] < pivot && i < j) j--;
            swap(nums, i ,j);
        }
        if (i == 2) return pivot;
        swap(nums, beginIndex, i);
        if (i > 2) return findNum(nums, beginIndex, i - 1);
        else return findNum(nums, i + 1, endIndex);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}