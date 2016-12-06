import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;

public class Solution {
    private HashMap<Integer, Integer> map = new HashMap<>();
    private HashSet<Step> set = new HashSet<>();

    public boolean canCross(int[] stones) {
        if (stones[1] != 1) return false;
        for (int i = 2, length = stones.length; i < length; ++i) {
            map.put(stones[i], i);
        }
        LinkedList<Step> queues = new LinkedList<>();
        queues.add(new Step(1, 1));
        while(!queues.isEmpty()) {
            Step currentStep = queues.pollFirst();
            if (set.contains(currentStep)) {
                continue;
            }

        }
        return false;
    }


    private static class Step {
        private final int index;
        private final int unit;

        private Step(int index, int unit) {
            this.index = index;
            this.unit = unit;
        }

        @Override
        public int hashCode() {
            return Objects.hash(index, unit);
        }

        @Override
        public boolean equals(Object o) {
            Step that = (Step) o;
            return index == that.index && unit == that.unit;
        }
    }
}