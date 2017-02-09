import java.util.Stack;

public class Solution {
    public String removeKdigits(String num, int k) {
        if (k >= num.length()) return "0";
        Stack<Character> stack = new Stack<>();
        int finalLength = num.length() - k;
        for (int i = 0, length = num.length(); i < length; ++ i) {
            char c = num.charAt(i);
            while(k > 0 && stack.size() > 0 && stack.peek() > c) {
                stack.pop();
                -- k;
            }
            stack.push(c);
        }
        while(stack.size() > finalLength) {
            stack.pop();
        }
        StringBuilder sb = new StringBuilder();
        while(stack.size() > 0) {
            sb.append(stack.pop());
        }
        while(sb.length() > 0 && sb.charAt(sb.length() - 1) == '0') {
            sb.deleteCharAt(sb.length() - 1);
        }
        StringBuilder r = sb.reverse();
        return r.length() == 0 ? "0" : r.toString();
    }
}