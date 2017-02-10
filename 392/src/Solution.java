import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    public boolean isSubsequence(String s, String t) {
        List<Integer>[] cache = new List[26];
        for (int i = 0, length = t.length(); i < length; ++ i) {
            char c = t.charAt(i);
            int index = c - 'a';
            if (cache[index] == null) cache[index] = new ArrayList<Integer>();
            cache[index].add(i);
        }
        int currentIndex = -1;
        for (int i = 0, length = s.length(); i < length; ++ i) {
            char c = s.charAt(i);
            int index = c - 'a';
            List<Integer> l = cache[index];
            if (l == null) return false;
            int searchIndex = Collections.binarySearch(l, currentIndex);
            int foundIndex = searchIndex >= 0 ? searchIndex : -searchIndex - 1;
            if (foundIndex >= l.size()) return false;
            currentIndex = l.get(foundIndex) + 1;
        }
        return true;
    }
}