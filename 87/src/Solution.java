import java.util.Stack;

public class Solution {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        for(int i = 0, length = heights.length; i <= length; ++ i) {
            int height = i == length ? 0 : heights[i];
            if (stack.isEmpty() || height >= heights[stack.peek()]) stack.add(i);
            else {
                int r = stack.pop();
                int count = stack.isEmpty() ? i : i - stack.peek() - 1;
                max = Math.max(max, heights[r] * count);
                -- i;
            }
        }
        return max;
    }
}