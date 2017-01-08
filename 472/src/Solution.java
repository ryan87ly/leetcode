import java.util.LinkedList;
import java.util.List;

public class Solution {
    private static final int CHAR_COUNT = 26;
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        TreeNode root = new TreeNode(' ', false);
        for (String word : words) {
            buildForWord(root, word);
        }
        LinkedList<String> result = new LinkedList<>();
        for (String word : words) {
            if (wordNumber(root, word, 0) > 1) {
                result.add(word);
            }
        }
        return result;
    }

    private int wordNumber(TreeNode root, String word, int index) {
        int wordLength = word.length();
        TreeNode currentNode = root;
        for (int i = index; i < wordLength; ++ i){
            char c = word.charAt(i);
            int charIndex = c - 'a';
            TreeNode nextNode = currentNode.children[charIndex];
            if (nextNode != null) {
                if (nextNode.isWord) {
                    if (i == wordLength - 1) {
                        return 1;
                    }
                    if (wordNumber(root, word, i + 1) > 0) {
                        return 2;
                    }
                }
                currentNode = nextNode;
            } else {
                return 0;
            }
        }
        return 0;
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