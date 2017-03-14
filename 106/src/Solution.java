public class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length == 0) return null;
        return buildSubtree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    private TreeNode buildSubtree(int[] inorder, int inOrderStartIndex, int inOrderEndIndex, int[] postorder, int postOrderStartIndex, int postOrderEndIndex) {
        if (inOrderStartIndex > inOrderEndIndex) return null;
        TreeNode currentRoot = new TreeNode(postorder[postOrderEndIndex]);
        int pos = findNodeIndex(inorder, inOrderStartIndex, inOrderEndIndex, postorder[postOrderEndIndex]);
        currentRoot.left = buildSubtree(inorder, inOrderStartIndex, pos - 1, postorder, postOrderStartIndex, postOrderStartIndex + pos - 1 - inOrderStartIndex);
        currentRoot.right = buildSubtree(inorder, pos + 1, inOrderEndIndex, postorder, postOrderStartIndex + pos - inOrderStartIndex, postOrderEndIndex - 1);
        return currentRoot;
    }

    private int findNodeIndex(int[] inorder, int startIndex, int endIndex, int value) {
        for (int i = startIndex; i <= endIndex; ++ i)  {
            if (inorder[i] == value) return i;
        }
        return -1;
    }
}