public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length;
        int length2 = nums2.length;
        int totalLength = length1 + length2;
        boolean isEven = totalLength % 2 == 0;
        int target = isEven ? totalLength / 2 : totalLength / 2 + 1;
        int bound1 = Math.min(target, length1);
        int bound2 = Math.min(target, length2);
        int a1 = 1, a2 = bound1;
        int b1 = 1, b2 = bound2;
        int i = (a1 + a2) / 2;
        int j = target - i;
        int selectedIndex;
        while(true) {
            if (i == 0) {
                selectedIndex = length1 + target;
                break;
            } else if (j == 0) {
                selectedIndex = target;
                break;
            } else if (i == bound1 + 1) {
                selectedIndex = target;
                break;
            } else if (j == bound2 + 1){
                selectedIndex = target - bound2;
                break;
            }
            int firstIndex = i - 1;
            int secondIndex = j - 1;
            if (nums1[firstIndex] <= nums2[secondIndex] && isValid(nums1, firstIndex, nums2[secondIndex])) {
                selectedIndex = length1 + j;
                break;
            } else if (nums1[firstIndex] >= nums2[secondIndex] && isValid(nums2, secondIndex, nums1[firstIndex])) {
                selectedIndex = i;
                break;
            } else if (nums1[firstIndex] < nums2[secondIndex]) {
                a1 = i + 1;
                b2 = j - 1;
                if (a1 >= bound1 + 1) {
                    i = bound1 + 1;
                } else {
                    i = (a1 + a2) / 2;
                }
                j = target - i;
            } else {
                b1 = j + 1;
                a2 = i - 1;
                if (b1 >= bound2 + 1) {
                    j = bound2 + 1;
                } else {
                    j = (b1 + b2) / 2;
                }
                i = target - i;
            }
        }
        int selectedValue = selectedNumber(nums1, nums2, selectedIndex);
        if (!isEven) return selectedValue;
        else {
            double n = nextNumber(nums1, i, nums2, j);
            double h = (selectedValue + nextNumber(nums1, i, nums2, j));
            return h / 2;
        }
    }

    private boolean isValid(int[] nums, int currentIndex, int targetNumber){
        if (nums.length <= currentIndex + 1) return true;
        else return targetNumber <= nums[currentIndex + 1];
    }

    private double nextNumber(int[] nums1, int currentIndex1, int[] nums2, int currentIndex2) {
        if (nums1.length <= (currentIndex1)) return nums2[currentIndex2];
        else if (nums2.length <= (currentIndex2)) return nums1[currentIndex1];
        else return Math.min(nums1[currentIndex1], nums2[currentIndex2]);
    }

    private int selectedNumber(int[] nums1, int[] nums2, int target) {
        if (nums1.length < target) return nums2[target - nums1.length - 1];
        else return nums1[target - 1];
    }
}