import java.util.*;

public class Solution {
    private Node[] words;
    private int minWords;

    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
        List<List<String>> result = new LinkedList<>();
        if (beginWord.equals(endWord)) {
            result.add(Arrays.asList(beginWord));
            return result;
        }
        wordList.add(beginWord);
        wordList.add(endWord);
        int wordLength = beginWord.length();
        if (isAdjacent(beginWord, endWord, wordLength)) {
            result.add(Arrays.asList(beginWord, endWord));
            return result;
        }
        int wordCount = wordList.size();
        Integer[] wordAccesses = new Integer[wordCount];
        words = new Node[wordCount];
        minWords = Integer.MAX_VALUE;
        int index = 2;
        words[0] = new Node(beginWord);
        words[1] = new Node(endWord);
        for (String word : wordList) {
            if (!word.equals(beginWord) && !word.equals(endWord))
                words[index++] = new Node(word);
        }
        for (int i = 0; i < wordCount - 1; ++ i) {
            for (int j = i + 1; j < wordCount; ++ j) {
                Node node1 = words[i];
                Node node2 = words[j];
                if (isAdjacent(node1.word, node2.word, wordLength)) {
                    node1.adjacentNodes.add(j);
                    node2.adjacentNodes.add(i);
                }
            }
        }
        Queue<SearchPoint> queue = new LinkedList<>();
        LinkedList<String> first = new LinkedList<>();
        first.add(beginWord);
        SearchPoint firstSearchPoint = new SearchPoint(0, first);
        queue.add(firstSearchPoint);
        while(!queue.isEmpty()) {
            SearchPoint sp = queue.poll();
            LinkedList<String> sequence = sp.sequence;
            int currentLevel = sequence.size();
            int currentIndex = sp.index;
            if (currentLevel > minWords) continue;
            Node currentWord = words[currentIndex];
            if (currentWord.adjacentNodes.contains(1)) {
                minWords = sequence.size();
                sequence.add(endWord);
                result.add(sequence);
                continue;
            }
            for (int i : currentWord.adjacentNodes) {
                Integer accessed = wordAccesses[i];
                if (accessed == null || accessed.intValue() >= currentLevel + 1) {
                    wordAccesses[i] = currentLevel + 1;
                    LinkedList<String> copied = new LinkedList<>(sequence);
                    copied.add(words[i].word);
                    queue.add(new SearchPoint(i, copied));
                }
            }
        }
        return result;
    }

    private boolean isAdjacent(String word1, String word2, int length) {
        int count = 0;
        for (int i = 0; i < length; ++ i) {
            if (word1.charAt(i) != word2.charAt(i)) {
                if (++ count >= 2) return false;
            }
        }
        return true;
    }

    private static class SearchPoint {
        private final int index;
        private final LinkedList<String> sequence;

        private SearchPoint(int index, LinkedList<String> sequence) {
            this.index = index;
            this.sequence = sequence;
        }
    }

    private static class Node {
        private final String word;
        private Set<Integer> adjacentNodes;

        private Node(String word) {
            this.word = word;
            adjacentNodes = new HashSet<>();
        }
    }
}