import java.util.HashMap;
import java.util.HashSet;

public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int length = s.length();
        if (length == 0) return 0;
        HashMap<Character, Integer> indexMap = new HashMap<>();
        int currentLength = 1;
        indexMap.put(s.charAt(0), 0);
        int maxLength = 1;
        for(int i = 1; i < length; ++i) {
            char c = s.charAt(i);
            int prevIndex = indexMap.containsKey(c) ? indexMap.get(c) : -1;
            currentLength = (i - prevIndex) > currentLength ? currentLength + 1 : i - prevIndex;
            maxLength = Math.max(maxLength, currentLength);
            indexMap.put(c, i);
        }
        return maxLength;
    }

    private HashSet<Character> newHashSet(Character c) {
        HashSet<Character> hs = new HashSet<>();
        hs.add(c);
        return hs;
    }
}