import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Solution {
    private Map<Identifier, Integer> map = new HashMap<>();

    public int findMaxForm(String[] strs, int m, int n) {
        int length = strs.length;
        Cost[] costs = new Cost[length];
        for(int i = 0; i < length; ++i) {
            String str = strs[i];
            int zero = count(str, '0');
            int one = count(str, '1');
            costs[i] = new Cost(zero, one);
        }
        return maxForm(costs, new Identifier(length - 1, m, n));
    }

    private int maxForm(Cost[] costs, Identifier identifier) {
        Integer i = map.get(identifier);
        if (i != null) return i;
        Cost c = costs[identifier.index];
        if (identifier.index == 0) {
            if (identifier.isEnough(c)) return 1;
            else return 0;
        }
        Integer r;
        if (identifier.isEnough(c)) r = Math.max(maxForm(costs, new Identifier(identifier.index - 1, identifier.zero - c.zero, identifier.one - c.one)) + 1,
                maxForm(costs, new Identifier(identifier.index - 1, identifier.zero, identifier.one)));
        else r = maxForm(costs, new Identifier(identifier.index - 1, identifier.zero, identifier.one));
        map.put(identifier, r);
        return r;
    }

    private int count(String str, char c) {
        int count = 0;
        for (int i = 0, length = str.length(); i < length; ++i ){
            if(str.charAt(i) == c) ++ count;
        }
        return count;
    }

    private static class Identifier {
        private int index;
        private int zero;
        private int one;
        private Integer h;

        private Identifier(int index, int zero, int one) {
            this.index = index;
            this.zero = zero;
            this.one = one;
        }

        public boolean isEnough(Cost c) {
            return zero >= c.zero && one >= c.one;
        }

        @Override
        public int hashCode() {
            if (h != null) return h;
            h = (index << 16) | (zero << 8) | one;
            return h;
        }

        @Override
        public boolean equals(Object o) {
            Identifier i = (Identifier) o;
            return index == i.index && zero == i.zero && one == i.one;
        }
    }

    private static class Cost {
        private int zero;
        private int one;

        private Cost(int zero, int one){
            this.zero = zero;
            this.one = one;
        }
    }
}