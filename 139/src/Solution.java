import java.util.List;

public class Solution {
    private static final int CHAR_COUNT = 26;
    private Boolean[] dp;

    public boolean wordBreak(String s, List<String> wordDict) {
        TreeNode root = new TreeNode(' ', false);
        dp = new Boolean[s.length()];
        for (String word : wordDict) {
            buildForWord(root, word);
        }
        return isWordBreak(root, s, 0);
    }

    private boolean isWordBreak(TreeNode root, String word, int index) {
        Boolean b = dp[index];
        if (b != null) return b;
        int wordLength = word.length();
        TreeNode currentNode = root;
        for (int i = index; i < wordLength; ++ i){
            char c = word.charAt(i);
            int charIndex = c - 'a';
            TreeNode nextNode = currentNode.children[charIndex];
            if (nextNode != null) {
                if (nextNode.isWord) {
                    if (i == wordLength - 1) {
                        dp[index] = true;
                        return true;
                    }
                    if (isWordBreak(root, word, i + 1)) {
                        dp[index] = true;
                        return true;
                    }
                }
                currentNode = nextNode;
            } else {
                dp[index] = false;
                return false;
            }
        }
        dp[index] = false;
        return false;
    }

    private void buildForWord(TreeNode root, String word) {
        int length = word.length();
        TreeNode currentNode = root;
        for (int i = 0; i < length; ++ i) {
            char c = word.charAt(i);
            boolean isWord = i == length - 1;
            currentNode = currentNode.nextNode(c, isWord);
        }
    }

    private static class TreeNode {
        private final char c;
        private boolean isWord;
        private TreeNode[] children;

        private TreeNode(char c, boolean isWord) {
            this.c = c;
            this.isWord = isWord;
            children = new TreeNode[CHAR_COUNT];
        }

        private TreeNode nextNode(char c, boolean isWord) {
            int index = c - 'a';
            TreeNode node = children[index];
            if (node == null) {
                node = new TreeNode(c, isWord);
                children[index] = node;
            }
            node.isWord |= isWord;
            return node;
        }
    }
}