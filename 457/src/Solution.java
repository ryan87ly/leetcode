public class Solution {
    public boolean circularArrayLoop(int[] nums) {
        int numLength = nums.length;
        if (numLength <= 1) return false;
        for (int i = 0; i < numLength; ++ i) {
            int currentNum = nums[i];
            if (currentNum == 0) continue;
            int direction = currentNum > 0 ? 1 : -1;
            int oneStepPointer = nextIndex(i, nums, numLength);
            int twoStepsPointer = nextIndex(oneStepPointer, nums, numLength);
            while(true) {
                if ((oneStepPointer == twoStepsPointer) && (nextIndex(oneStepPointer, nums, numLength) != oneStepPointer)) return true;
                int oneStepNum = nums[oneStepPointer];
                if (oneStepNum == 0 || (oneStepNum ^ direction) < 0 || (oneStepNum % numLength == 0)) {
                    clean(i, nums, numLength, direction);
                    break;
                }
                int twoStepsNum = nums[twoStepsPointer];
                if (twoStepsNum == 0 || (twoStepsNum ^ direction) < 0 || (twoStepsNum % numLength == 0)) {
                    clean(i, nums, numLength, direction);
                    break;
                }
                oneStepPointer = nextIndex(oneStepPointer, nums, numLength);
                twoStepsPointer = nextIndex(nextIndex(twoStepsPointer, nums, numLength), nums, numLength);
            }
        }
        return false;
    }

    private void clean(int startIndex, int[] nums, int length, int direction) {
        for (int i = startIndex; i < length; ++ i) {
            int currentNum = nums[i];
            if((currentNum != 0 && (currentNum ^ direction) > 0) || (currentNum % length == 0)) nums[i] = 0;
            else return;
        }
    }

    private int nextIndex(int currentIndex, int[] nums, int length) {
        int currentNum = nums[currentIndex];
        return circularMod(currentIndex + currentNum, length);
    }

    private int circularMod(int num, int length) {
        int r = num % length;
        if (r < 0) return r + length;
        else return r;
    }
}