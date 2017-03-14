/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p == q) return p;
        TreeNode left = p.val < q.val ? p : q;
        TreeNode right = left == p ? q : p;
        return calculate(root, left, right);
    }

    private TreeNode calculate(TreeNode current, TreeNode left, TreeNode right) {
        if (current == null) return null;
        if (current.val >= left.val && current.val <= right.val) {
            return current;
        } else if (current.val < left.val) {
            return calculate(current.right, left, right);
        } else {
            return calculate(current.left, left, right);
        }
    }
}