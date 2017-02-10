import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Solution {
    public String removeDuplicateLetters(String s) {
        List<Integer>[] charPosList = new List[26];
        boolean[] charUsed = new boolean[26];
        for (int i = 0, length = s.length(); i < length; ++ i) {
            char c = s.charAt(i);
            List<Integer> l = charPosList[c - 'a'];
            if (l == null) charPosList[c - 'a'] = new ArrayList<>();
            charPosList[c - 'a'].add(i);
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0, length = s.length(); i < length; ++ i) {
            char c = s.charAt(i);
            int index = c - 'a';
            if (charUsed[index]) continue;
            while(!stack.isEmpty() && stack.peek() > c) {
                char peekValue = stack.peek();
                int intValue = peekValue - 'a';
                List<Integer> l = charPosList[intValue];
                int searchIndex = Collections.binarySearch(l, i);
                int posIndex = - searchIndex - 1;
                if (posIndex < l.size()) {
                    charUsed[intValue] = false;
                    stack.pop();
                } else {
                    break;
                }
            }
            stack.push(c);
            charUsed[index] = true;
        }
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }
}