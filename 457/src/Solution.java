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
                if (oneStepPointer == twoStepsPointer && nextIndex(oneStepPointer, nums, numLength) != oneStepPointer) return true;
                int oneStepNum = nums[oneStepPointer];
                int twoStepsNum = nums[twoStepsPointer];

                if ((oneStepNum ^ direction) < 0) {
                    //clean(i, oneStepPointer, nums);
                    break;
                }
                if ()
            }
        }
        return false;
        /*

        while(true) {
            int step = nums[currentIndex];
            if (step == 0) return true;
            if (step % numLength == 0) return false;
            nums[currentIndex] = 0;
            currentIndex = circularMod(currentIndex + step, numLength);
        }*/
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