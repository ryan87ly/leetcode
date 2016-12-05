import com.sun.tracing.dtrace.StabilityLevel;

import java.util.HashMap;
import java.util.Objects;

public class Solution3 {
    private HashMap<Node, Integer> map = new HashMap<>();
    public int getMoneyAmount(int n) {
        if (n <= 1) return 0;
        return f(0, n);
    }

    private int f(int min, int max) {
        Node node = new Node(min, max);
        Integer v = map.get(node);
        if (v != null) return v;
        int diff = max - min;
        if (diff == 0) return 0;
        if (diff == 1) return min;
        if (diff == 2) return min + 1;
        if (diff == 3) return min + max - 1; // 1st, 3th
        int currentMin = Integer.MAX_VALUE;
        for (int i = 1; i < diff; ++i){
            int selected = min + i;
            currentMin = Math.min(Math.max(f(min, selected - 1), f(selected + 1, max)) + selected, currentMin);
        }
        map.put(node, currentMin);
        return currentMin;
    }

    private class Node {
        private final int min;
        private final int max;

        private Node(int min, int max) {
            this.min = min;
            this.max = max;
        }

        @Override
        public int hashCode() {
            return Objects.hash(min, max);
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Node)) return false;
            Node that = (Node) o;
            return min == that.min && max == that.max;
        }
    }
}