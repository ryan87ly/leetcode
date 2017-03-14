import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        if (root == null) return result;
        Map<Integer, Integer> levels = new HashMap<>();
        access(root, levels, 0);
        for (int i = 0; i < levels.size(); ++ i) {
            if (levels.containsKey(i)) {
                result.add(levels.get(i));
            }
        }
        return result;
    }

    private void access(TreeNode current, Map<Integer, Integer> levels, int currentLevel) {
        if (current.right != null) access(current.right, levels, currentLevel + 1);
        if (!levels.containsKey(currentLevel)) {
            levels.put(currentLevel, current.val);
        }
        if (current.left != null) access(current.left, levels, currentLevel + 1);
    }
}